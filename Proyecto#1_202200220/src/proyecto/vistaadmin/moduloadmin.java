/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.vistaadmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static proyecto.shared.DataStorage.ListaCurso;
import static proyecto.shared.DataStorage.ListaProfe;
import static proyecto.shared.DataStorage.ListaAlumno;
import com.itextpdf.text.Paragraph;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 *
 * @author Paola
 */

@SuppressWarnings("serial")
public final class moduloadmin extends javax.swing.JFrame { 
    
    public static DefaultTableModel model;
           
    public moduloadmin() {
        initComponents();
        actualizarTablaProfe();
        actualizarTablaCurso();
        actualizarTablaAlumno();
        GraficoDePie pieChartPanel = new GraficoDePie("Porcentaje de Género de Profesores", this);
        jPanel6.add(pieChartPanel.getContentPane());
        pieChartPanel.getContentPane().setOpaque(true);
        jPanel6.revalidate();
        jPanel6.repaint();
    }
    

    public static void actualizarTablaProfe() {

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Correo");
        model.addColumn("Genero");
        for (Profesor p : ListaProfe) {
            String[] fila = new String[5];
            fila[0] = String.valueOf(p.getCodigo());
            fila[1] = p.getNombre();
            fila[2] = p.getApellido();
            fila[3] = p.getCorreo();
            fila[4] = p.getGenero();
            model.addRow(fila);
        }
        
        
        tablaProfesor.setModel(model);
    }
    
    public static void actualizarTablaCurso() {

        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Creditos");
        model.addColumn("Alumnos");
        model.addColumn("Profesor");
        for (Curso c : ListaCurso) {
            String[] fila = new String[5];
            fila[0] = String.valueOf(c.getCodigo());
            fila[1] = c.getNombre();
            fila[2] = c.getCreditos();
            fila[3] = c.getAlumnos();
            fila[4] = c.getProfesor();
            model.addRow(fila);
        }
        
        
        tablaCurso.setModel(model);
    }
    
    
    public static void actualizarTablaAlumno() {

        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Correo");
        model.addColumn("Genero");
        
        for (Alumno a : ListaAlumno) {
            String[] fila = new String[5];
            fila[0] = String.valueOf(a.getCodigo());
            fila[1] = a.getNombre();
            fila[2] = a.getApellido();
            fila[3] = a.getCorreo();
            fila[4] = a.getGenero();
            model.addRow(fila);
        }
        
        
        tablaAlumno.setModel(model);
    }
    
    
    public static void eliminarProfesor(String codigo) {
        for (int i = 0; i < proyecto.shared.DataStorage.ListaProfe.size(); i++) {
            Profesor profesor = proyecto.shared.DataStorage.ListaProfe.get(i);
            if (profesor.getCodigo().equals(codigo)) {
                proyecto.shared.DataStorage.ListaProfe.remove(i);
                break;
            }
        }
    }
    
    public static void eliminarCurso(String codigo) {
        for (int i = 0; i < proyecto.shared.DataStorage.ListaCurso.size(); i++) {
            Curso curso = proyecto.shared.DataStorage.ListaCurso.get(i);
            if (curso.getCodigo().equals(codigo)) {
                proyecto.shared.DataStorage.ListaCurso.remove(i);
                break;
            }
        }
    }
      
    
    private void exportarTablaProfesorAPDF() {
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar archivo PDF");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Título del PDF
            Paragraph title = new Paragraph("Lista de Profesores");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Crear una tabla para la lista de profesores
            PdfPTable pdfTable = new PdfPTable(tablaProfesor.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Agregar encabezados de columna
            for (int i = 0; i < tablaProfesor.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tablaProfesor.getColumnName(i)));
                pdfTable.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int row = 0; row < tablaProfesor.getRowCount(); row++) {
                for (int column = 0; column < tablaProfesor.getColumnCount(); column++) {
                    PdfPCell cell = new PdfPCell(new Phrase(tablaProfesor.getValueAt(row, column).toString()));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "La tabla de profesores se ha exportado a PDF con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar la tabla a PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    
    
    private void exportarTablaCursoAPDF() {
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar archivo PDF");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Título del PDF
            Paragraph title = new Paragraph("Lista de Cursos");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Crear una tabla para la lista de profesores
            PdfPTable pdfTable = new PdfPTable(tablaCurso.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Agregar encabezados de columna
            for (int i = 0; i < tablaCurso.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tablaCurso.getColumnName(i)));
                pdfTable.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int row = 0; row < tablaCurso.getRowCount(); row++) {
                for (int column = 0; column < tablaCurso.getColumnCount(); column++) {
                    PdfPCell cell = new PdfPCell(new Phrase(tablaCurso.getValueAt(row, column).toString()));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "La tabla de cursos se ha exportado a PDF con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar la tabla a PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
    
    
    private void exportarTablaAlumnoAPDF() {
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar archivo PDF");

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Título del PDF
            Paragraph title = new Paragraph("Lista de Alumnos");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Crear una tabla para la lista de profesores
            PdfPTable pdfTable = new PdfPTable(tablaAlumno.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Agregar encabezados de columna
            for (int i = 0; i < tablaAlumno.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tablaAlumno.getColumnName(i)));
                pdfTable.addCell(cell);
            }

            // Agregar datos de la tabla
            for (int row = 0; row < tablaAlumno.getRowCount(); row++) {
                for (int column = 0; column < tablaAlumno.getColumnCount(); column++) {
                    PdfPCell cell = new PdfPCell(new Phrase(tablaAlumno.getValueAt(row, column).toString()));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(this, "La tabla de Alumnos se ha exportado a PDF con éxito.");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar la tabla a PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProfesor = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCurso = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAlumno = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 0));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Listado oficial");

        tablaProfesor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Género"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProfesor);
        if (tablaProfesor.getColumnModel().getColumnCount() > 0) {
            tablaProfesor.getColumnModel().getColumn(0).setResizable(false);
            tablaProfesor.getColumnModel().getColumn(1).setResizable(false);
            tablaProfesor.getColumnModel().getColumn(2).setResizable(false);
            tablaProfesor.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setText("Crear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel6.setInheritsPopupMenu(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton2.setText("Carga Masiva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Exportar listado a PDF");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Profesores", jPanel1);

        jLabel2.setText("Listado Oficial");

        tablaCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Créditos", "Alumnos", "Profesor"
            }
        ));
        jScrollPane2.setViewportView(tablaCurso);

        jButton6.setText("Crear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Carga Masiva");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Actualizar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Exportar listado a PDF");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7)))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cursos", jPanel2);

        jLabel4.setText("Listado Oficial");

        tablaAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Género"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaAlumno);

        jButton11.setText("Carga Masiva");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Exportar listado a PDF");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Alumnos", jPanel3);

        jButton13.setText("Cerrar Sesión");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void procesarArchivoCSV(File file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Divide la línea CSV en partes usando la coma como delimitador
            String[] parts = line.split(",");
            
            // Verifica si hay suficientes elementos en la línea
            if (parts.length >= 5) {
                String codigo = parts[0].trim();
                String nombre = parts[1].trim();
                String apellido = parts[2].trim();
                String correo = parts[3].trim();
                String genero = parts[4].trim();
                
                
                // Añade el usuario a tu lista o modelo de datos (por ejemplo, DefaultTableModel)
                // y establece la contraseña por defecto (1234)
                Profesor profesor = new Profesor(codigo, nombre, apellido, correo, genero, "1234");
                ListaProfe.add(profesor); // Agrega el nuevo profesor a la lista

                // Agrega el nuevoUsuario a tu lista de usuarios o modelo de datos
            }
        }
        
        // Refresca la tabla o lista después de cargar los usuarios
        actualizarTablaProfe();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar el archivo CSV.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void procesarArchivoCSVAlumno(File file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Divide la línea CSV en partes usando la coma como delimitador
            String[] parts = line.split(",");
            
            // Verifica si hay suficientes elementos en la línea
            if (parts.length >= 5) {
                String codigo = parts[0].trim();
                String nombre = parts[1].trim();
                String apellido = parts[2].trim();
                String correo = parts[3].trim();
                String genero = parts[4].trim();
                
                
                // Añade el usuario a tu lista o modelo de datos (por ejemplo, DefaultTableModel)
                // y establece la contraseña por defecto (1234)
                Alumno alumno = new Alumno(codigo, nombre, apellido, correo, genero, "1234");
                ListaAlumno.add(alumno); // Agrega el nuevo profesor a la lista

                // Agrega el nuevoUsuario a tu lista de usuarios o modelo de datos
            }
        }
        actualizarTablaAlumno();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar el archivo CSV.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    
    private void procesarArchivoCSVCurso(File file) {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Divide la línea CSV en partes usando la coma como delimitador
            String[] parts = line.split(",");
            
            // Verifica si hay suficientes elementos en la línea
            if (parts.length >= 5) {
                String codigo = parts[0].trim();
                String nombre = parts[1].trim();
                String creditos = parts[2].trim();
                String alumnos = parts[3].trim();
                String profesor = parts[4].trim();
                
                
                // Añade el usuario a tu lista o modelo de datos (por ejemplo, DefaultTableModel)
                // y establece la contraseña por defecto (1234)
                Curso curso = new Curso(codigo, nombre, creditos, alumnos, profesor, "1234");
                ListaCurso.add(curso); // Agrega el nuevo profesor a la lista

                // Agrega el nuevoUsuario a tu lista de usuarios o modelo de datos
            }
        }
        actualizarTablaCurso();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al procesar el archivo CSV.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = tablaProfesor.getSelectedRow();
        if (selectedRow != -1) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar al profesor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                String codigo = (String) tablaProfesor.getValueAt(selectedRow, 0);
                eliminarProfesor(codigo);
                actualizarTablaProfe(); // Actualiza la tabla después de eliminar
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un profesor para eliminar.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        exportarTablaProfesorAPDF();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int selectedRow = tablaCurso.getSelectedRow();
            if (selectedRow != -1) {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este curso?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                String codigo = (String) tablaCurso.getValueAt(selectedRow, 0);
                eliminarCurso(codigo);
                actualizarTablaCurso(); // Actualiza la tabla después de eliminar
            }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un curso para eliminar.");
            }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        exportarTablaCursoAPDF();        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo CSV para carga masiva");
    
        int userSelection = fileChooser.showOpenDialog(this);
    
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        
        // Llama a un método para procesar el archivo CSV seleccionado
            procesarArchivoCSVAlumno(selectedFile);
        }        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        exportarTablaAlumnoAPDF();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Nuevoprofe log = new Nuevoprofe(this);
        log.setVisible(true);
        this.dispose();

                   // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = tablaProfesor.getSelectedRow();

    if (selectedRow != -1) {
        // Obtener los valores de las celdas de la fila seleccionada
        String codigo = tablaProfesor.getValueAt(selectedRow, 0).toString();
        String nombre = tablaProfesor.getValueAt(selectedRow, 1).toString();
        String apellido = tablaProfesor.getValueAt(selectedRow, 2).toString();
        String correo = tablaProfesor.getValueAt(selectedRow, 3).toString();
        String genero = tablaProfesor.getValueAt(selectedRow, 4).toString();

        // Crear una instancia de la ventana de actualización y pasar los datos
        actualizar updateWindow = new actualizar(this, codigo, nombre, apellido, correo, genero);
        updateWindow.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un profesor para actualizar.");
    } 
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int selectedRow = tablaCurso.getSelectedRow();

            if (selectedRow != -1) {
                // Obtener los valores de las celdas de la fila seleccionada
                String codigo = tablaCurso.getValueAt(selectedRow, 0).toString();
                String nombre = tablaCurso.getValueAt(selectedRow, 1).toString();
                String creditos = tablaCurso.getValueAt(selectedRow, 2).toString();
                String alumnos = tablaCurso.getValueAt(selectedRow, 3).toString();
                String profesor = tablaCurso.getValueAt(selectedRow, 4).toString();

                // Crear una instancia de la ventana de actualización y pasar los datos
                actualizarcurso updateWindow = new actualizarcurso(this, codigo, nombre, creditos, alumnos, profesor);
                updateWindow.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un profesor para actualizar.");
            }   
                  
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Nuevocurso log = new Nuevocurso(this);
        log.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo CSV para carga masiva");
    
        int userSelection = fileChooser.showOpenDialog(this);
    
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        
        // Llama a un método para procesar el archivo CSV seleccionado
            procesarArchivoCSV(selectedFile);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo CSV para carga masiva");
    
        int userSelection = fileChooser.showOpenDialog(this);
    
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        
        // Llama a un método para procesar el archivo CSV seleccionado
            procesarArchivoCSVCurso(selectedFile);
        }        // TODO add your handling code here:        
    }//GEN-LAST:event_jButton7ActionPerformed

  
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new moduloadmin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTable tablaAlumno;
    private static javax.swing.JTable tablaCurso;
    private static javax.swing.JTable tablaProfesor;
    // End of variables declaration//GEN-END:variables
}
//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona perfecto
//funciona bien