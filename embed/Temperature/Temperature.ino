#include <DHT.h>
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>
#include <WiFiClient.h>

#define DHTPIN 4      //Data Pin
#define DHTTYPE DHT11 //Sensor Type

#define STASSID ""   //SSID
#define STAPSK "" //Password

IPAddress server_addr(0,0,0,0);
char user[] = "";              // MySQL user login username
char password[] = "";        // MySQL user login password

WiFiClient client;
MySQL_Connection conn((Client *)&client);

DHT dht(DHTPIN, DHTTYPE); //DHT Definition

ESP8266WebServer server(80); //Server Port

float h = 0; //Humidity
float t = 0; //Temperature Celsius
float f = 0; //Temperature Fahrenheit

float hic = 0; //Heat index Celsius
float hif = 0; //Heat index Fahrenheit

void handle()
{
  String data = "h:" + String(h) + "," +
                "t:" + String(t) + "," +
                "f:" + String(f) + "," +
                "hic:" + String(hic) + "," +
                "hif:" + String(hif) + "";

  server.send(200, "text/plain", data );
}

void handleNotFound()
{
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET) ? "GET" : "POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i = 0; i < server.args(); i++)
  {
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
}

void printSerial()
{
  Serial.print(F("Humidade: "));
  Serial.print(h);
  Serial.print(F("%  Temperature: "));
  Serial.print(t);
  Serial.print(F("°C "));
  Serial.print(f);
  Serial.print(F("°F  Heat index: "));
  Serial.print(hic);
  Serial.print(F("°C "));
  Serial.print(hif);
  Serial.println(F("°F"));
}

void wifiSetup()
{
  WiFi.mode(WIFI_STA);
  WiFi.begin(STASSID, STAPSK);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(STASSID);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  if (MDNS.begin("temperature"))
  {
    Serial.println("MDNS responder started");
  }

  server.on("/", handle);

  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("HTTP server started");

}

void databaseSetup()
{
  Serial.println("Connecting to MySQL");

  if (conn.connect(server_addr, 3306, user, password)) {
      Serial.println( "Connected to Database" );
  }
  else
    Serial.println("Connection failed.");
  conn.close();
}

void dhtSetup()
{
  dht.begin();
  Serial.println("DHT sensor started");
}

void setup()
{
  Serial.begin(9600);

  wifiSetup();

  //databaseSetup();

  dhtSetup();
}

void loop()
{
  delay(2000);

  h = dht.readHumidity();
  t = dht.readTemperature();
  f = dht.readTemperature(true);

  if (isnan(h) || isnan(t) || isnan(f))
  {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  hif = dht.computeHeatIndex(f, h);
  hic = dht.computeHeatIndex(t, h, false);

  printSerial();

  server.handleClient();
  MDNS.update();
}
