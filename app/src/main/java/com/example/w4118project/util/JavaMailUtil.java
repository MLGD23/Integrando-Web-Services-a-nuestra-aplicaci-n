package com.example.w4118project.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil extends AsyncTask {

    private String EMAIL;
    private String PASSWORD;
    private Exception exception;

    @Override
    protected Object doInBackground(Object[] objects) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL, PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
            this.exception = e;
            progressDialog.dismiss();
        }
        return null;
    }

    //Declaring Variables
    private Context context;
    private Session session;
    private Activity activity;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public JavaMailUtil(Context context, Activity activity, String email, String subject, String message){
        //Initializing variables
        this.context = context;
        this.activity = activity;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Enviando mensaje","Favor de esperar...",false,false);
        this.exception = null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //Dismissing the progress dialog
        progressDialog.dismiss();
        if(this.exception == null) {
            //Showing a success message
            Toast.makeText(context,"Mensaje enviado",Toast.LENGTH_LONG).show();
            this.activity.finish();
        }
        else {
            //Showing a success message

            if (this.exception instanceof AuthenticationFailedException) {
                Toast.makeText(context,"Error: credenciales de correo invalidas",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,"Error al enviar mensaje",Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
