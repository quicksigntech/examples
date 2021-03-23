/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author boggi
 */
public class SignatureResult implements Serializable {
    public String iss = "";
    public String aud = "";
    public String sub = "";
    public String bankid_sub = "";
    public String birthdate = "";
    public String name = "";
    public String bankid_altsub = "";
    public String ssn = "";
    public String uniqueuserid = "";
    public String certissuer = "";
    public String certsubject = "";
    public List<QuickSignEvidence> evidence = null;
    public Long iat = null;
    public Long nbf = null;
    public Long exp = null;
}
