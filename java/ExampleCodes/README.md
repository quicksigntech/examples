<h1>How to use</h1>
Copy QuickSign.java and DTO' files into your project, and the libraries of you don't have them already.

Use the functions:
<ul>
<li>SigningBatch <b>startSigning</b>(List<SigningDocument> files, List<SigningUser> users, String email) to start the signing process</li>
<li>SigningBatch <b>getBatch</b>(String id) to monitor the signing process</li>
<li>SigningDocument <b>getFullySignedDocument</b>(String batchId, String documentId) to fetch the signed document</li>
<li><b>deleteBatch</b>(String batchId) to delete the signing process</li>
</ul>

Enjoy.
