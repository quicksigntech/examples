<h1>Java example code</h1>
<p>All you need are 4 functions to ingrate signing into your system</p>
<ul>
<li>public SigningBatch <b>startSigning</b>(List<SigningDocument> files, List<SigningUser> users, String email) to start the signing process</li>
<li>public SigningBatch <b>getBatch</b>(String id) to monitor the signing process</li>
<li>public SigningDocument <b>getFullySignedDocument</b>(String batchId, String documentId) to fetch the signed document</li>
<li>public <b>deleteBatch</b>(String batchId) to delete the signing process</li>
</ul>

You will find the functions in Main.java. The functions are dependant on three libraries.
<ul>
<li>Gson</li>
<li>Http client</li>
<li>Http core</li>
<li>commons-logging</li>
</ul>
