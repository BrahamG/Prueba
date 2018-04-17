//package MisClases;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public final class TapJPanel extends JFrame
{
    private JPanel panel1,panel2,panel3,panelA,panelB,panelC,panel4;
  	private JLabel[] labels;
  	private JTextField[] text;
    private JButton editar, eliminar, nuevo;
    private JTable table;
    private DefaultTableModel dtm;
    private int filas,id;
    public TapJPanel(Object [][]d, String[] cN,int id)
    {
        super("Formulario");
        this.id=id;
        iniciaComponentes(d,cN);
        table.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                tableMouseClicked(evt);
            }
        });
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    public void iniciaComponentes(Object [][]d, String[] cN)
    {
        panel1=new JPanel();panel2=new JPanel();panel3=new JPanel();panelA=new JPanel();
        panelB=new JPanel();panelC=new JPanel();panel4=new JPanel();
        dtm= new DefaultTableModel(d,cN);
        table = new JTable(dtm)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scrollPane = new JScrollPane(table);

        labels= new JLabel[cN.length];
    	text= new JTextField[cN.length];
    	crearl(labels,text, cN);
        editar=new JButton("Editar");
        eliminar=new JButton("Eliminar");
        nuevo=new JButton("Nuevo");

        editar.addMouseListener(new MyClickListener2());
        eliminar.addMouseListener(new MyClickListener3());
        nuevo.addMouseListener(new MyClickListener1());

        panel2.setLayout(new GridLayout(3,2));
    	panelB.setLayout(new GridLayout(4,2));
   		for(int i=0; i<labels.length; i++)
    	{
    		panel2.add(labels[i]);
			panel2.add(text[i]);
      		if(i>2)
      		{
        		panelB.add(labels[i]);
        		panelB.add(text[i]);
      		}
		} 
		panelA.add(panel2);
   		panelC.add(panelB);
    	panel4.setLayout(new FlowLayout());
    	panel4.add(panelA);panel4.add(panelC);
    	panel3.add(nuevo);panel3.add(editar);panel3.add(eliminar);
    	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	add(panel4);add(panel3);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        Object[] newColumn= {0,1,2};
        dtm.addColumn("ID",newColumn);
    }
    public void crearl(JLabel[] l,JTextField[] t, String []cN)
    {
    	for(int i=0; i<cN.length; i++)
    	{
    		l[i]= new JLabel (cN[i]);
      		t[i]= new JTextField(10);
      		t[i].setEnabled(false);
    	}
  	}
  	public static void main (String [] args)
    {
        int id=2;
        Object[][] datos = {{"11/13/18","12:00","Tecnológico", "Monterrey", "Alebrijes",3,4},{"09/10/17","17:00","Azul", "Cruz Azul", "America",2,1},{"22/05/18","17:00","Azteca","México","España",1,1}};
        String[] columnNames = {"Fecha","Hora","Estadio","Equipo local","Equipo visitante","Goles Local","Goles visitante"};
        TapJPanel formulario = new TapJPanel(datos,columnNames,id);
    }
    private class MyClickListener1 extends MouseAdapter
    {
    	DefaultTableModel model;
    	Object [] fila;
    	boolean ban=false;
        @Override
    	public void mouseClicked(MouseEvent event)
    	{
			if(null != nuevo.getText())
      		switch (nuevo.getText()) 
      		{
      			case "Guardar":
                    model= (DefaultTableModel) table.getModel();
                    fila= new Object[labels.length+1];
                    if(bandera())
                    {
                        fila[labels.length]=++id;
                        model.addRow(fila);
                        table.setModel(model);
                    }
                    nuevo.setText("Nuevo");
                    for(int i=0;i<labels.length;i++)
   						text[i].setEnabled(false);
                    break;
                case "Nuevo":
                    agrega();
                    nuevo.setText("Guardar");
                    for(int i=0;i<labels.length;i++)
       					text[i].setEnabled(true);
                    break;
            }
    	}
        public void agrega()
        {
            for(int i=0; i<labels.length; i++)
            {
                text[i].setText(null);
            }
        }
        public boolean bandera()
        {
			for(int i=0; i<labels.length; i++)
    	  	{
      			if("".equals(text[i].getText()))
      			{
      				ban=false;
      				break;
      			}
      			else
      			{      						
      				fila[i]=text[i].getText();
        			text[i].setText(null);
        			ban=true;
      			}   
    	  	}
      		return ban;
        }
    }
    private class MyClickListener2 extends MouseAdapter
    {
    	boolean ban=false;
    	String[]datos;
        @Override
        public void mouseClicked(MouseEvent event)
    	{
      		DefaultTableModel model = (DefaultTableModel) table.getModel();
      		int a = table.getSelectedRow(); 
      		if (a<0)
	        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla" ); 
         	else 
         	{
         		if(null != editar.getText())
                switch (editar.getText()) 
                {
                	case "Editar":
                		for(int i=0;i<labels.length;i++)
                        	text[i].setEnabled(true);
                            editar.setText("Guardar");
                    break;
                	case "Guardar":
                		datos=new String[table.getColumnCount()];
                		if(bandera())
                		{
                			for(int i=0 ; i<table.getColumnCount()-1;i++)
                            	model.setValueAt(datos[i], filas, i);
                        	editar.setText("Editar");
                        	for(int s=0;s<labels.length;s++)
                        	{
                            	text[s].setEnabled(false);
                        	}
                		}
                		break;
                }
         	}
		}
		public boolean bandera()
        {
			for(int i=0; i<labels.length; i++)
    	  	{
      			if("".equals(text[i].getText()))
      			{
      				ban=false;
      				break;
      			}
      			else
      			{      						
      				for(int x=0; x<table.getColumnCount()-1; x++)
                        datos[x]=text[x].getText();
                    ban=true;
      			}   
    	  	}
      		return ban;
        }
    }
    private class MyClickListener3 extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent event)
        {
        	DefaultTableModel model = (DefaultTableModel) table.getModel(); 
	        int a = table.getSelectedRow(); 
	        if (a<0)
	        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla" ); 
         	else 
         	{
         		int confirmar=JOptionPane.showConfirmDialog(null,"Esta seguro que desea Eliminar el registro? "); 
 				if(JOptionPane.YES_OPTION==confirmar) 
 				{
 					model.removeRow(a); 
                    agrega();
 				   	JOptionPane.showMessageDialog(null,"Registro Eliminado" );
 				}
 				if(JOptionPane.NO_OPTION==confirmar)
 					JOptionPane.showMessageDialog(null,"No hubo cambios");
 			}
	 	}
        public void agrega()
        {
            for(int i=0; i<labels.length; i++)
            {
                text[i].setText(null);
            }
        }
    }
    private void tableMouseClicked(MouseEvent evt)
    {
    	int filasele= table.getSelectedRow();
    	for(int i=0; i<labels.length; i++)
    		text[i].setText(table.getValueAt(filasele, i).toString());
    	filas=filasele;
  	}
}