package com.example.studente.appcucinaproject.Timer;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrea on 15/05/2016.
 */

public class Ticker {
    private Responder Eventi;
    private java.util.Timer timer;
    private long millesec;
    private long SleepTime;

    public Ticker(Responder c, long speed,long tot) {
        Eventi = c;
        millesec=tot;
        SleepTime=speed;
    }


    public void Pause()
    {
        timer.cancel();
    }

    public void Start()
    {
        timer = new java.util.Timer();
        if(millesec<SleepTime)
            SleepTime=millesec;

        timer.scheduleAtFixedRate(new TickerTask(),0,SleepTime);
    }

    private class TickerTask extends TimerTask {
        public void run () {

            if(millesec==0)
            {
                Eventi.End();
                timer.cancel();
                return;
            }
            else
            {
                millesec-=SleepTime;
                if(millesec==0)
                {
                    Eventi.End();
                    timer.cancel();
                    return;
                }
                else if(millesec<SleepTime)
                {
                    try {
                        Thread.sleep(millesec);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Ticker.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    timer.cancel();
                    Eventi.End();
                    return;
                }

            }
            //se è 0, end

            Eventi.Tick(millesec);
        }
    }
}


interface Responder{
    void Tick(long MillisecRimasti);
    void End();
}