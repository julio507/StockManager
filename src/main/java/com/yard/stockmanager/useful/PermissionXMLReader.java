package com.yard.stockmanager.useful;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Processa o documento XML com o uso da API SAX.
 * <p>
 * A classe "XMLReader" é derivada da classe "DefaultHandler" da biblioteca
 * org.xml.sax.helpers.DefaultHandler. Isso faz com que "XMLReader" “ganhe”
 * automaticamente um processador SAX com o comportamento default.
 *
 * @author Eduardo Corrêa Gonçalves
 */
public class PermissionXMLReader extends DefaultHandler {

    private String tagAtual;
    private String nivel;
    private String ativoAcesso;
    private String nivelDeAcesso;
    private String regra;
    private String tab;
    private boolean acesso;

    /**
     * construtor default
     *
     * @param nivelDeAcesso;
     * @param regra;
     * @param tab;
     */
    public PermissionXMLReader(String nivelDeAcesso, String regra, String tab) {
        super();
        this.nivelDeAcesso = nivelDeAcesso;
        this.regra = regra;
        this.tab = tab;
    }

    /**
     * Método que executa o parsing: laço automático que varre o documento de
     * início ao fim, disparando eventos relevantes
     *
     * @param pathArq;
     */
    public void fazerParsing(String pathArq) {

        // cria instância da classe SAXParser, através da factory
        // SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;

        try {
            saxParser = factory.newSAXParser();

            //comanda o início do parsing
            saxParser.parse(pathArq, this);
            // o "this" indica que a própria
            // classe atuará como
            // gerenciadora de eventos SAX.

            //tratamento de exceções.
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Error.log("Nào foi possivel ler o Arquivo", this.getClass());
            StringBuffer msg = new StringBuffer();
            msg.append("Erro:\n");
            msg.append(e.getMessage() + "\n");
            msg.append(e.toString());
            System.out.println(msg);
        }
    }

    // os métodos startDocument, endDocument, startElement, endElement e
    // characters, listados a seguir, representam os métodos de call-back da API
    // SAX

    /**
     * evento startDocument do SAX. Disparado antes do processamento da primeira
     * linha
     */
    public void startDocument() {
    }

    /**
     * evento endDocument do SAX. Disparado depois do processamento da última
     * linha
     */
    public void endDocument() {
    }

    /**
     * evento startElement do SAX. disparado quando o processador SAX identifica
     * a abertura de uma tag. Ele possibilita a captura do nome da tag e dos
     * nomes e valores de todos os atributos associados a esta tag, caso eles
     * existam.
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {

        // recupera o nome da tag atual
        tagAtual = qName;

        // se a tag for "<pais>", recupera o valor do atributo "sigla"
        if (qName.compareTo("nivel") == 0) {
            nivel = atts.getValue(0);
        }
        if (qName.compareTo("permissao") == 0) {
            ativoAcesso = atts.getValue(0);
        }
    }

    /**
     * evento endElement do SAX. Disparado quando o processador SAX identifica o
     * fechamento de uma tag
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        tagAtual = "";
    }

    /**
     * evento characters do SAX. É onde podemos recuperar as informações texto
     * contidas no documento XML (textos contidos entre tags).
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String regraXml = new String(ch, start, length);

        //Tratamento da informações da tag atual
        if (tagAtual.compareTo("permissao") == 0) {

            if (nivel.equals(nivelDeAcesso) && regraXml.split("\\.")[0].equals(tab) && regraXml.split("\\.")[1].equals(regra)) {
                if (ativoAcesso.equals("true")) {
                    this.acesso = true;
                } else if (ativoAcesso.equals("false")) {
                    this.acesso = false;
                }
            }
        }
    }

    /**
     * Método que verifica se usuario tem permissão na tela
     */
    public boolean hasAccess() {
        return acesso;
    }
}