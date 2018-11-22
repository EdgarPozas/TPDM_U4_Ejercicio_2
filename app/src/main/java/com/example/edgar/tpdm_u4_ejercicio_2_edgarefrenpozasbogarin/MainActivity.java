package com.example.edgar.tpdm_u4_ejercicio_2_edgarefrenpozasbogarin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView estado;
    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    private Button[][] botones;
    private Gato gato;
    public boolean mi_turno;
    private boolean iniciado;
    private int turnos;
    private int gano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado=findViewById(R.id.estado);
        btn_1=findViewById(R.id.btn1);
        btn_2=findViewById(R.id.btn2);
        btn_3=findViewById(R.id.btn3);
        btn_4=findViewById(R.id.btn4);
        btn_5=findViewById(R.id.btn5);
        btn_6=findViewById(R.id.btn6);
        btn_7=findViewById(R.id.btn7);
        btn_8=findViewById(R.id.btn8);
        btn_9=findViewById(R.id.btn9);
        gato=new Gato();
        mi_turno=true;
        botones=new Button[3][3];
        botones[0][0]=btn_1;
        botones[1][0]=btn_2;
        botones[2][0]=btn_3;
        botones[0][1]=btn_4;
        botones[1][1]=btn_5;
        botones[2][1]=btn_6;
        botones[0][2]=btn_7;
        botones[1][2]=btn_8;
        botones[2][2]=btn_9;
    }

    public void iniciar(View view) {
        iniciado=true;
        estado.setText("Jugador");
        btn_1.setText("");
        btn_2.setText("");
        btn_3.setText("");
        btn_4.setText("");
        btn_5.setText("");
        btn_6.setText("");
        btn_7.setText("");
        btn_8.setText("");
        btn_9.setText("");
        btn_1.setEnabled(true);
        btn_2.setEnabled(true);
        btn_3.setEnabled(true);
        btn_4.setEnabled(true);
        btn_5.setEnabled(true);
        btn_6.setEnabled(true);
        btn_7.setEnabled(true);
        btn_8.setEnabled(true);
        btn_9.setEnabled(true);
        turnos=1;
        gano=0;
        gato.reset();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (iniciado) {
                    if (mi_turno)
                        continue;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            estado.setText("Máquina");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int[] val=gato.evaluar(turnos);

                    if(val[0]==-1){
                        iniciado=false;
                        break;
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                seleccionar(val[0], val[1], -1);
                                if(val.length>2){
                                    if(val[2]==-1) {
                                        gano=1;
                                        iniciado = false;
                                    }
                                }
                            }
                        });
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mi_turno=true;
                    turnos++;
                    estado.setText(gano==0?"Jugador":"Gano máquina");
                }
            }
        }).start();

    }

    public void sel_9(View view) {
        if(!mi_turno)
            return;
        seleccionar(2,2,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_8(View view) {
        if(!mi_turno)
            return;
        seleccionar(1,2,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_7(View view) {
        if(!mi_turno)
            return;
        seleccionar(0,2,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_6(View view) {
        if(!mi_turno)
            return;
        seleccionar(2,1,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_5(View view) {
        if(!mi_turno)
            return;
        seleccionar(1,1,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_4(View view){
        if(!mi_turno)
            return;
        seleccionar(0,1,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_3(View view) {
        if(!mi_turno)
            return;
        seleccionar(2,0,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_2(View view) {
        if(!mi_turno)
            return;
        seleccionar(1,0,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;
    }
    public void sel_1(View view) {
        if(!mi_turno)
            return;
        seleccionar(0,0,1);
        if(validar_gane()){
            iniciado=false;
            estado.setText("Gano jugador");
        }
        mi_turno=false;

    }
    private void seleccionar(int x,int y,int valor){

        botones[x][y].setText(valor==-1?"O":"X");
        botones[x][y].setEnabled(false);
        gato.setValor(x,y,valor);
    }

    private boolean validar_gane(){
         return (gato.matriz[0][0] == 1 && gato.matriz[0][1] == 1 && gato.matriz[0][2] == 1)||
                 (gato.matriz[0][0] == 1 && gato.matriz[1][1] == 1 && gato.matriz[2][2] == 1)||
                 (gato.matriz[0][0] == 1 && gato.matriz[1][0] == 1 && gato.matriz[2][0] == 1)||
                 (gato.matriz[0][1] == 1 && gato.matriz[1][1] == 1 && gato.matriz[2][1] == 1)||
                 (gato.matriz[0][2] == 1 && gato.matriz[1][1] == 1 && gato.matriz[2][0] == 1)||
                 (gato.matriz[0][2] == 1 && gato.matriz[1][2] == 1 && gato.matriz[2][2] == 1)||
                 (gato.matriz[1][0] == 1 && gato.matriz[1][1] == 1 && gato.matriz[1][2] == 1)||
                 (gato.matriz[2][0] == 1 && gato.matriz[2][1] == 1 && gato.matriz[2][2] == 1);
    }


    class Gato{
        public int[][] matriz;

        public Gato(){
            reset();
        }
        public void reset(){
            matriz=new int[3][3];
        }

        public void setValor(int x,int y,int valor){
            matriz[x][y]=valor;
        }
        public int[] evaluar(int turno){
            int[] pos=new int[2];

            for (int v:new int[]{-1,1}) {

                if (matriz[0][0] == v && matriz[0][1] == v && matriz[0][2] == 0) {
                    return new int[]{0, 2,v};
                }
                if (matriz[0][0] == v && matriz[1][0] == v && matriz[2][0] == 0) {
                    return new int[]{2, 0,v};
                }
                if (matriz[0][0] == v && matriz[1][1] == v && matriz[2][2] == 0) {
                    return new int[]{2, 2,v};
                }

                if (matriz[0][1] == v && matriz[0][2] == v && matriz[0][0] == 0) {
                    return new int[]{0, 0,v};
                }
                if (matriz[0][1] == v && matriz[1][1] == v && matriz[2][1] == 0) {
                    return new int[]{2, 1,v};
                }

                if (matriz[0][2] == v && matriz[1][1] == v && matriz[2][0] == 0) {
                    return new int[]{2, 0,v};
                }
                if (matriz[0][2] == v && matriz[1][2] == v && matriz[2][2] == 0) {
                    return new int[]{2, 1,v};
                }

                if (matriz[1][0] == v && matriz[2][0] == v && matriz[0][0] == 0) {
                    return new int[]{2, 0,v};
                }
                if (matriz[1][0] == v && matriz[1][1] == v && matriz[1][2] == 0) {
                    return new int[]{1, 2,v};
                }

                if (matriz[1][1] == v && matriz[1][2] == v && matriz[1][0] == 0) {
                    return new int[]{1, 0,v};
                }
                if (matriz[1][1] == v && matriz[2][1] == v && matriz[0][1] == 0) {
                    return new int[]{0, 1,v};
                }
                if (matriz[1][1] == v && matriz[2][2] == v && matriz[0][0] == 0) {
                    return new int[]{0, 0,v};
                }

                if (matriz[1][2] == v && matriz[2][2] == v && matriz[1][0] == 0) {
                    return new int[]{1, 0,v};
                }

                if (matriz[2][0] == v && matriz[1][1] == v && matriz[0][2] == 0) {
                    return new int[]{0, 2,v};
                }
                if (matriz[2][0] == v && matriz[2][1] == v && matriz[2][2] == 0) {
                    return new int[]{2, 2,v};
                }


                if (matriz[2][1] == v && matriz[2][2] == v && matriz[2][0] == 0) {
                    return new int[]{2, 0,v};
                }

                if (matriz[0][0] == v && matriz[2][2] == v && matriz[1][1] == 0) {
                    return new int[]{1, 1,v};
                }
                if (matriz[0][0] == v && matriz[0][2] == v && matriz[0][1] == 0) {
                    return new int[]{0, 1,v};
                }
                if (matriz[0][0] == v && matriz[2][0] == v && matriz[1][0] == 0) {
                    return new int[]{1, 0,v};
                }

                if (matriz[0][1] == v && matriz[2][1] == v && matriz[1][1] == 0) {
                    return new int[]{1, 1,v};
                }

                if (matriz[0][2] == v && matriz[2][2] == v && matriz[1][2] == 0) {
                    return new int[]{1, 2,v};
                }
                if (matriz[0][2] == v && matriz[2][0] == v && matriz[1][1] == 0) {
                    return new int[]{1, 1,v};
                }

                if (matriz[1][0] == v && matriz[1][2] == v && matriz[1][1] == 0) {
                    return new int[]{1, 1,v};
                }

                if (matriz[1][2] == v && matriz[1][0] == v && matriz[1][1] == 0) {
                    return new int[]{1, 1,v};
                }

                if (matriz[2][0] == v && matriz[2][2] == v && matriz[2][1] == 0) {
                    return new int[]{2, 1,v};
                }
            }

            boolean permitido=false;
            int x=-1;
            int y=-1;
            int contador=0;
            do{
                x=(int)(Math.random()*2);
                y=(int)(Math.random()*2);
                permitido=matriz[x][y]==0;
                contador++;
            }while(!permitido&&contador<20);

            return new int[]{x,y};
        }
    }

}
