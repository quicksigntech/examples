/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

/**
 *
 * @author boggi
 */
public class SigningDocument {
    /* Name of file */
    public String name = "";
    /* BASE64 ENCODED PDF FILE */
    public String data = "";
    /* Document id given by quicksign */
    public String documentId = "";
    /* application/pdf */
    public String type = "";
    public long size = 0;
}
