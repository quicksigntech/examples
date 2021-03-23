/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

import java.util.List;

/**
 *
 * @author boggi
 */
public class StartSigningBody {
    public List<SigningDocument> documents;
    public List<SigningUser> users;
    public String secretKey = "";
    public String mailCompleteSignRequestTo = "";
}
