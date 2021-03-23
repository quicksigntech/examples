/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

import java.io.Serializable;

/**
 *
 * @author boggi
 */
public class QuickSignEvidence implements Serializable {
    public String signedDocumentSha256 = "";
    public String padesSignedPdf = "";
    public String description = "";
    public String unsignedDocumentSha256 = "";
}
