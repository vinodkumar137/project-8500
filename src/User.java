public class User {
    private String name, email, password, phno;

    /**
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this(null, email, password, null);
    }

    /**
     * @param name
     * @param email
     * @param password
     * @param phno
     */
    public User(String name, String email, String password, String phno) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhno(phno);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = SHA256Helper.hash(password);
    }

    /**
     * @return the phno
     */
    public String getPhno() {
        return phno;
    }

    /**
     * @param phno the phno to set
     */
    public void setPhno(String phno) {
        this.phno = phno;
    }

}
