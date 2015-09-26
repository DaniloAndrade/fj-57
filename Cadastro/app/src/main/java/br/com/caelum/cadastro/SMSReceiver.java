package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import br.com.caelum.cadastro.dao.AlunoDAO;

/**
 * Created by android5243 on 26/09/15.
 */
public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] smss = (Object[]) bundle.get("pdus");

       byte[] msg = (byte[]) smss[0];

        SmsMessage smsMessage = SmsMessage.createFromPdu(msg);

        String fone = smsMessage.getDisplayOriginatingAddress();



        AlunoDAO alunoDAO = new AlunoDAO(context);

        fone = fone.substring(3,fone.length());
        boolean ehAluno = alunoDAO.isAluno(fone);

        alunoDAO.close();

        Log.i("SMS_RECEIVER",fone);


        if (ehAluno){
            Toast.makeText(context, "Chegou um SMS de : " + fone, Toast.LENGTH_SHORT).show();
            Log.i("SMS_RECEIVER","é aluno: "+fone);
        }


    }
}
