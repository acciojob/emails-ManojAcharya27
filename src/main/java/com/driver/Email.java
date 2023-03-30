package com.driver;

public class Email {

    private String emailId;
    private String password;

    private  boolean length=false;
    private  boolean upperCase=false;
    private boolean lowerCase=false;
    private boolean digit=false;
    private  boolean splChar=false;

    public Email(String emailId){
        this.emailId = emailId;
          this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }
    public String getPassword() {
        return this.password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLength() {
        return length;
    }

    public void setLength(boolean length) {
        this.length = length;
    }

    public boolean isUpperCase() {
        return upperCase;
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

    public boolean isLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(boolean lowerCase) {
        this.lowerCase = lowerCase;
    }

    public boolean isDigit() {
        return digit;
    }

    public void setDigit(boolean digit) {
        this.digit = digit;
    }

    public boolean isSplChar() {
        return splChar;
    }

    public void setSplChar(boolean splChar) {
        this.splChar = splChar;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(password)){

            if(isValid(newPassword)){
                setPassword(newPassword);
            }else return;
        }

    }
    public boolean isValid(String temp){
        if(temp.length()>=8) this.length=true;
        for (int i=0;i<temp.length();i++){
            char c=temp.charAt(i);
            if(c>='0'&&c<='9') digit=true;
            else if(c>='A'&&c<='Z') upperCase=true;
            else if(c>='a'&&c<='z') lowerCase=true;
            else splChar=true;
        }
        if(splChar&&lowerCase&&length&&upperCase&&digit) return true;
        return false;
    }
}
