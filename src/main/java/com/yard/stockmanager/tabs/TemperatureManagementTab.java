
package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.entity.Sensor;
import com.yard.stockmanager.useful.Error;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * TemperatureManagementTab
 */
public class TemperatureManagementTab extends Tab{

    private CloseableHttpClient client = HttpClients.createDefault();

    private Font font = new Font( 42 );

    public TemperatureManagementTab() {
        super( "Temperatura" );

        initComponent();

        startTread();
    }

    private void startTread()
    {
        Runnable thread = new Runnable(){
        
            @Override
            public void run() {
                try
                {
                    while( true )
                    {
                        HttpGet get = new HttpGet( "http://temperature.local" );
                        
                        try (CloseableHttpResponse response = client.execute(get)) {

                            HttpEntity entity = response.getEntity();
                
                            if (entity != null) {
                                String result = EntityUtils.toString(entity);

                                String[] s = result.split( "," );

                                Platform.runLater( new Runnable() {
									
									@Override
									public void run() {
									    temp.setText( s[1].split(":")[1] + "ºC" );
                                        hum.setText( s[0].split(":")[1] + "%" );
                                    }
								});
                            }
                        }

                        catch( Exception e )
                        {
                            Error.messageAndLog( e.getMessage() );
                        }

                        Thread.sleep( 500 );
                    }
                }

                catch( Exception e )
                {
                    Error.messageAndLog(e.getMessage());
                }
            }
        };

        Thread t1 = new Thread( thread );

        t1.start();
    }

    private void initComponent()
    {
        humLabel.setFont( font );
        tempLabel.setFont( font );
        hum.setFont( font );
        temp.setFont( font );

        grid.addRow( 0, humLabel, hum );
        grid.addRow( 1, tempLabel, temp );

        border.setLeft( grid );
        border.setCenter( chart );

        setContent( border );
    }

    CategoryAxis xAxis = new CategoryAxis();
    CategoryAxis yAxis = new CategoryAxis();
    
    private LineChart chart = new LineChart( xAxis, yAxis );

    private BorderPane border = new BorderPane();
    private GridPane grid = new GridPane();

    private Label temp = new Label( "0ºc" );
    private Label hum = new Label( "0%" );

    private Label tempLabel = new Label( "Temperatura:" );
    private Label humLabel = new Label( "Humidade:" );
}