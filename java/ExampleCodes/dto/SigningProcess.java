/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boggi
 */
public class SigningProcess {
    public String redirectUrl = "";
    public boolean completed = false;
    public SigningUser signingUser = null;
    public List<DocumentData> documents = new ArrayList();
    public SignatureResult signature;
    public String batchId;
}
