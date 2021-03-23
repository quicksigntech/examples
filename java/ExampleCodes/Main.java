package ExampleCodes;

import ExampleCodes.dto.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {

    private String addr = "https://app.quicksign.tech";
    private String secretKey = "";
    
    public static void main(String[] args) {
        Main main = new Main();
        main.setSecretKey("key-found-in-quicksign-portal");
        
        String batchId = main.startSigningExample();
        main.batchExample(batchId);
        main.getFullySignedDocument(batchId);
    }

    /**
     * An example on how to start the signing process.
     * This is a all in one api call with everything you need to get the document signed.
     * @return 
     */
    public String startSigningExample() {
        List<SigningDocument> documents = new ArrayList();
        SigningDocument document = new SigningDocument();
        document.data = getDocument();
        document.name = "quicksign.pdf";
        document.type = "application/pdf";
        documents.add(document);
        
        SigningUser user = new SigningUser();
        user.name = "Name of signer";
        user.email = "email.to@signer.com";
        
        List<SigningUser> users = new ArrayList();
        users.add(user);
        
        SigningBatch signingBatch = startSigning(documents, users, "your@email.com");
        //The batch id can be used to get status of signer later on, see example existing batchExample();
        printBatch(signingBatch);
        return signingBatch.id;
    }

    
    public SigningBatch startSigning(List<SigningDocument> files, List<SigningUser> users, String email) {
        StartSigningBody body = new StartSigningBody();
        body.secretKey = this.secretKey;
        body.documents = files;
        body.users = users;
        body.mailCompleteSignRequestTo = email;
        
        String result = postDocument(body, "qapi/quicksignwithsecret/");
        if(result != null) {
            Gson gson = new Gson();
            return gson.fromJson(result, SigningBatch.class);
        }
        
        return null;
    }

    private String postDocument(Object object, String endpoint) {
        try {
            String postUrl = addr + endpoint;// put in your url

            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            builder.setPrettyPrinting();
            builder.serializeNulls();
            builder.serializeSpecialFloatingPointValues();
            Gson gson = builder.create();


            String body = gson.toJson(object);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(body, ContentType.APPLICATION_JSON);
            postingString.setContentEncoding("UTF-8");
            post.setEntity(postingString);
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json"  + ";charset=UTF-8");

            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            
            return responseString;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // @Deprecated httpClient.getConnectionManager().shutdown(); 
        }
        return null;
    }

    private void setSecretKey(String key) {
        this.secretKey = key;
    }

    /**
     * You can always get the signing batch and see who has signed or not signed.
     * @param batchId 
     */
    private void batchExample(String batchId) {
        try {
            // Endpoint : /qapi/{secretkey}/batch/{batchid}
            String endpoint = addr + "/qapi/"+this.secretKey+"/batch/"+batchId;
            String responseString = getQuery(endpoint);
            if(responseString == null || responseString.isEmpty()) {
                System.out.println("Failed to find batch, is the secret key and the batch id correct?");
                return;
            }
            
            Gson gson = new Gson();
            SigningBatch batch = gson.fromJson(responseString, SigningBatch.class);
            printBatch(batch);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printBatch(SigningBatch signingBatch) {
        System.out.println("Batch id : " + signingBatch.id);
        
        //There is one signing process for each user that need to sign the documents.
        for(SigningProcess process : signingBatch.batch) {
            System.out.println("Signer " + process.signingUser.name);
            System.out.println("\thas signing link: " + process.redirectUrl + " has completed signature: " + process.completed);
            if(process.completed) {
                System.out.println("\t Got signed by: " + process.signature.name);
                System.out.println("\t Cert issuer: " + process.signature.certissuer);
                System.out.println("\t Birth date: " + process.signature.birthdate);
            }
        }
    }
        
    /**
     * Returns a base64 encoded pdf with the QuickSign logo.
     * @return 
     */
    private String getDocument() {
        return "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";
    }

    /**
     * List all documents fully signed by all users.
     * It will only shown when everyone has signed.
     * Endpoint: /qapi/{secret}/signed/{batchId}
     * @param batchId 
     */
    private void getFullySignedDocument(String batchId) {
        String endpoint = addr + "/qapi/"+this.secretKey+"/signed/"+batchId;
        String documentData = getQuery(endpoint);
        Gson gson = new Gson();
        List<SigningDocument> docs = gson.fromJson(documentData, ArrayList.class);
        if(docs.isEmpty()) {
            System.out.println("Signed documents are not ready yet.");
            return;
        }
        
        for(SigningDocument doc : docs) {
            System.out.println("Signed document: " + doc.name + " base64 size: " + doc.data.length());
        }
    }

    private String getQuery(String endpoint) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(endpoint);
            HttpResponse httpresponse = httpclient.execute(httpget);
            String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
            return responseString;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}