import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Formulario extends JFrame
{
    private JLabel texto,lnombre,lAp,lAm,lcelular,lCP,ldireccion,lemail,ledad,larroba;
    private JTextField nombre,apellidoP,apellidoM,celular,cP,direccion,email,edad,dominio;
    private JButton cancelar,registrar;
    public Formulario()
    {
        super("Registro");
        configurarVentana();
        iniciaComponentes();
    }
    public void configurarVentana()
    {
        setSize(600,550);
        //this.pack();                                  // colocamos tamanio a la ventana (ancho, alto)
        setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        setLayout(null);                                    // no usamos ningun layout, solo asi podremos dar posiciones a los componentes                              
        //this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(java.awt.Color.white);
    }
    public void iniciaComponentes()
    {
        //iniciamos componentes
        lnombre=new JLabel("Ingresa nombre");
        lAp=new JLabel("Apellido Paterno");
        lAm=new JLabel("Apellido Materno (opcional)");
        lcelular=new JLabel("Celular");
        lCP=new JLabel("Codigo Postal");
        ldireccion=new JLabel("Direccion");
        lemail=new JLabel("Correo");
        larroba=new JLabel("@");
        ledad=new JLabel("Edad");

        nombre=new JTextField("");
        apellidoP=new JTextField("");
        apellidoM=new JTextField("");
        celular=new JTextField("");
        cP=new JTextField("");
        direccion=new JTextField("");
        email=new JTextField("");
        dominio=new JTextField("");
        edad=new JTextField("");

        cancelar=new JButton("Cerrar");
        registrar=new JButton("Registrar");
        cancelar.setBounds(204,480,101,22);
        registrar.setBounds(300,480,101,22);
        cancelar.addMouseListener(new MyClickListener());
        registrar.addMouseListener(new MyClickListener1());

        lnombre.setBounds(140,80,250,22);
        lAp.setBounds(133,120,250,22);
        lAm.setBounds(57,160,250,22);
        lcelular.setBounds(200,200,250,22);
        lCP.setBounds(154,240,250,22);
        ldireccion.setBounds(186,280,250,22);
        lemail.setBounds(204,320,250,22);
        ledad.setBounds(216,360,250,22);
        larroba.setBounds(393,320,100,22);

        nombre.setBounds(300,80,200,22);
        apellidoP.setBounds(300,120,200,22);
        apellidoM.setBounds(300,160,200,22);
        celular.setBounds(300,200,200,22);
        cP.setBounds(300,240,200,22);
        direccion.setBounds(300,280,200,22);
        email.setBounds(300,320,200,22);
        edad.setBounds(300,360,200,22);
        texto=new JLabel("REGISTRAR UN ALUMNO");
        texto.setBounds(20,20,250,22);

        add(lnombre);add(nombre);
        add(lAp);add(apellidoP);
        add(lAm);add(apellidoM);
        add(lcelular);add(celular);
        add(lCP);add(cP);
        add(ldireccion);add(direccion);
        add(lemail);add(email);
        add(ledad);add(edad);
        add(texto);add(cancelar);add(registrar);

        nombre.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarN(evt);
            }
        });
        apellidoP.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarN(evt);
            }
        });
        apellidoM.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarN(evt);
            }
        });
        celular.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarNum(evt);
            }
        });
        cP.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarCP(evt);
            }
        });
        direccion.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarD(evt);
            }
        });
        email.addFocusListener(new java.awt.event.FocusAdapter() 
        {
            public void focusLost(java.awt.event.FocusEvent evt) 
            {
                validarEmail(evt);
            }
        });
        edad.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                validarEdad(evt);
            }
        });
    }
    public static void main(String[] args) {
        new Formulario();      // creamos una ventana
    }
    private class MyClickListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent event)
        {
            System.exit(1);
        }
    }
    private class MyClickListener1 extends MouseAdapter
    {
        public void mouseClicked(MouseEvent event)
        {
            char n=nombre.getText().charAt(0);
            char ap=apellidoP.getText().charAt(0);
            char am=apellidoM.getText().charAt(0);
            char d=direccion.getText().charAt(0);
            if(nombre.getText()==""||Character.isLowerCase(n))
            {
                nombre.requestFocus();
                JOptionPane.showMessageDialog(null,"Inicial nombre debe ser mayuscula");
            }
            else
                if(apellidoP.getText()==""||Character.isLowerCase(ap))
                {
                    apellidoP.requestFocus();
                    JOptionPane.showMessageDialog(null,"Apellido vacio o incorrecto");
                }
                else
                    if(celular.getText()=="")
                    {
                        celular.requestFocus();
                        JOptionPane.showMessageDialog(null,"Campo vacio");
                    }
                    else
                        if(cP.getText()=="")
                        {
                            cP.requestFocus();
                            JOptionPane.showMessageDialog(null,"Campo vacio");
                        }
                        else
                            if(direccion.getText()==""||Character.isLowerCase(d))
                            {
                                direccion.requestFocus();
                                JOptionPane.showMessageDialog(null,"Inicial debe ir en mayuscula");
                            }
                            else
                                if(email.getText()=="")
                                {
                                    email.requestFocus();
                                    JOptionPane.showMessageDialog(null,"Campo vacio");
                                }
                                else
                                    if(edad.getText()=="")
                                    {
                                        edad.requestFocus();
                                        JOptionPane.showMessageDialog(null,"Campo vacio");
                                    }
                                    else
                                        JOptionPane.showMessageDialog(null,"Registrado con exito"+"\n"+
                                            nombre.getText()+"\n"+apellidoP.getText()+"\n"+apellidoM.getText()+
                                            "\n"+celular.getText()+"\n"+email.getText()+"\n:)");
        }
    }
    private void validarN(java.awt.event.KeyEvent evt) 
    {                                                 //Metodo que valida entrada de caracteres
        char car=evt.getKeyChar();
        if(car<'A'||car>'Ú'&&car=='_'||car=='{'||car=='|'||car=='}'||car=='['||car==']'||car=='/'||car=='~'||car=='°'||car=='¿'||car=='¡'||car=='¬'||car=='^')
            evt.consume();
    }
    private void validarNum(java.awt.event.KeyEvent evt) 
    {                                                 //Metodo que valida entrada de caracteres
        char car=evt.getKeyChar();
        if(car<'-'||car>'9')
            evt.consume();
    }
    private void validarCP(java.awt.event.KeyEvent evt) 
    {
        char car=evt.getKeyChar();
        if(car<'0'||car>'9')
            evt.consume();
        String c=cP.getText();
        if(c.length()>=5)
            evt.consume();
    }
    private void validarEdad(java.awt.event.KeyEvent evt) 
    {
        char car=evt.getKeyChar();
        if(car<'0'||car>'9')
            evt.consume();
        String c=edad.getText();
        if(c.length()>=3)
            evt.consume();
    }
    private void validarD(java.awt.event.KeyEvent evt) 
    {
        String c=direccion.getText();
        if(c.length()>=40)
            evt.consume();
    }
    //Método que valida el correo uwu
    public boolean isEmail(String correo) 
    {
        boolean bandera=true;

        Pattern p = Pattern.compile("^.|^@");
        Matcher m = p.matcher(correo);
        p = Pattern.compile("^www.");
        m = p.matcher(correo);
        if (m.find())
        {
            bandera=false;
            //JOptionPane.showMessageDialog(null,"Los emails no empiezan por www");
        }
        // comprueba que contenga @
        p = Pattern.compile("@");
        m = p.matcher(correo);
        if (!m.find())
        {
            bandera=false;
            //JOptionPane.showMessageDialog(null,"La cadena no tiene arroba");
        }

        p = Pattern.compile(".co|.com|.org|.eu|.net|.es|.tv|.ito|.edu|.mx");
        m = p.matcher(correo);
        if (!m.find())
        {
            bandera=false;
            //JOptionPane.showMessageDialog(null,"La cadena no tiene un dominio");
        }

        p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]{2,}+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        m = p.matcher(correo);
        if (m.find()) 
            bandera=true;
        else 
            bandera=false;

        return bandera;
    }
    private void validarEmail(java.awt.event.FocusEvent evt)
    {
        if(isEmail(email.getText()))
        {}
        else
        {
            JOptionPane.showMessageDialog(null,"Email no valido");
            email.requestFocus();
        }
    }
}
