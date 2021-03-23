/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleCodes.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boggi
 */
public class SigningBatch {
    public String id;
    public List<SigningProcess> batch = new ArrayList();
    public List<DocumentData> documents = new ArrayList();
    public boolean completed = false;
}
