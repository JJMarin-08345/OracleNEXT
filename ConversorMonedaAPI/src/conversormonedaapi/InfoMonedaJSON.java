/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversormonedaapi;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InfoMonedaJSON {
    private String conversion_result;
    private Gson gson;
    private final String urlBaseRequest = "https://v6.exchangerate-api.com/v6/e2e3c6965b0e8b7b49ded04f/pair";
    
    public InfoMonedaJSON() {
        gson = new Gson();
    }

    public String conversionMoneda(String monedaBase, String monedaConvertir, String Valor) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(urlBaseRequest+'/'+monedaBase+'/'+monedaConvertir+'/'+Valor))
                .build();
        HttpResponse<String> response = client
                .send(req, HttpResponse.BodyHandlers.ofString());
        
        InfoMonedaJSON obj = gson.fromJson(response.body(), InfoMonedaJSON.class);
        this.conversion_result = obj.getConversion_result().replace('.', ',');
        System.out.println(Valor+" ("+ monedaBase+ ") ----> "+this.conversion_result+" ("+monedaConvertir+")");
        return conversion_result;
    }
    
    public String getConversion_result() {
        return conversion_result;
    }
    
    
}
