package ibf2022.batch2.csf.backend.models;

public class Extract {

    byte[] filedata;
    String filename;
    String filetype;

    @Override
    public String toString() {
        return "Extract [filedatasize=" + filedata.length + ", filename=" + filename + ", filetype=" + filetype
                + "]";
    }

    public Extract(byte[] filedata, String filename, String filetype) {
        this.filedata = filedata;
        this.filename = filename;
        this.filetype = filetype;
    }

    public Extract() {
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

}
