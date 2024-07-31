package gr.ed.technikon.models;

public class Owner {
    
    private long VatNumber;
    private int Id;
    private String Name;
    private String SurName;
    private String Address;
    private String PhoneNumber;
    private String Email;
    private String Username;
    private String password;

    public Owner(long VatNumber, int Id , String Name, String SurName, String Address, String PhoneNumber, String Email, String Username, String password) {
        this.VatNumber = VatNumber;
        this.Id = Id;
        this.Name = Name;
        this.SurName = SurName;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Username = Username;
        this.password = password;
    }

    public long getVatNumber() {
        return VatNumber;
    }

    public void setVATNumber(long VatNumber) {
        this.VatNumber = VatNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String SurName) {
        this.SurName = SurName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Owner{" + "VATNumber=" + VatNumber + 
                ", Name=" + Name + ", SurName=" + SurName + 
                ", Address=" + Address + 
                ", PhoneNumber=" + PhoneNumber + 
                ", Email=" + Email + 
                ", Username=" + Username + 
                ", password=" + password + '}';
    }
    
}
