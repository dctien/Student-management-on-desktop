/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILayer;

import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumn;
import DataLayer.AddManager;
import DataLayer.AddStudent;
import static DataLayer.Class_DataLayer.SearchAllClassForChart;
import DataLayer.DeleteManager;
import DataLayer.DeleteStudent;
import DataLayer.EditManager;
import DataLayer.Event_DataLayer;
import static DataLayer.Event_DataLayer.SearchAllEventForChart;
import DataLayer.LimittedTextField;
import static DataLayer.Organization_DataLayer.SearchAllOrganizationForChart;
import DataLayer.Participation_DataLayer;
import DataLayer.ShowManager;
import DataLayer.ShowStudent;
import static DataLayer.Student_DataLayer.SearchAllStudentForChart;
import Model.Class;
import Model.Event;
import Model.Organization;
import Model.Student;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author --Client-ServEr--
 */
public class GUI extends javax.swing.JFrame {

    Login Login = new Login(this, true);
    EditStudent Edit = new EditStudent(this, true);
    AboutSMX about = new AboutSMX(this, true);
    Date todayD = new Date(System.currentTimeMillis());

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        cycleThruChildren(this.Login);
        cycleThruChildren(this.about);

        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dayFormat.format(todayD.getTime());
        this.JLBDate.setText(today);
        this.JLBWelcome.setText("You are not logged in. Please login!");
        Container a = this.getContentPane();
        a.setBackground(new java.awt.Color(31, 34, 43));
        setIconForButton();
    }

    private void setIconForButton() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JBTShow.setIcon(new javax.swing.ImageIcon("E:\\ProjectDatabase\\NHOM FULL\\Desktop (2)\\search1.png"));
                JBTAdd.setIcon(new javax.swing.ImageIcon("E:\\ProjectDatabase\\NHOM FULL\\Desktop (2)\\update.png"));
                JBTEdit.setIcon(new javax.swing.ImageIcon("E:\\ProjectDatabase\\NHOM FULL\\Desktop (2)\\edit copy.png"));
                JBTDelete.setIcon(new javax.swing.ImageIcon("E:\\ProjectDatabase\\NHOM FULL\\Desktop (2)\\delete copy.png"));
            }
        });
    }

//    private void filterNumber() {
//        ((AbstractDocument) JTFAttendeesTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
//            Pattern regEx = Pattern.compile("\\d*");
//
//            @Override
//            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
//                Matcher matcher = regEx.matcher(text);
//                if (!matcher.matches()) {
//                    return;
//                }
//                super.replace(fb, offset, length, text, attrs);
//            }
//        });
//    }

    private static void cycleThruChildren(Container c) {
        Component[] cc = c.getComponents();
        for (int i = 0; i < cc.length; i++) {
            //System.out.println(cc[i].getClass().getName());  
            if (cc[i] instanceof JPanel) {
                ((JPanel) cc[i]).setBackground(Color.getColor("0A1020"));
            }
            cycleThruChildren((Container) cc[i]);
        }
    }

    public static Date convertdate(java.util.Date utilDate) {
        Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new Date(utilDate.getTime());
        }
        return sqlDate;
    }

    public static class Animate {

        public static final int RUN_TIME = 400;
        private JPanel panel;
        private Rectangle from;
        private Rectangle to;

        private long startTime;

        public Animate(JPanel panel, Rectangle from, Rectangle to) {
            this.panel = panel;
            this.from = from;
            this.to = to;
        }

        public void start() {
            Timer timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long duration = System.currentTimeMillis() - startTime;
                    double progress = (double) duration / (double) RUN_TIME;
                    if (progress > 1f) {
                        progress = 1f;
                        ((Timer) e.getSource()).stop();
                    }
                    Rectangle target = calculateProgress(from, to, progress);
                    panel.setBounds(target);
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            startTime = System.currentTimeMillis();
            timer.start();
        }

    }

    public static class Animate1 {

        public static final int RUN_TIME = 400;
        private JDesktopPane panel;
        private Rectangle from;
        private Rectangle to;

        private long startTime;

        public Animate1(JDesktopPane panel, Rectangle from, Rectangle to) {
            this.panel = panel;
            this.from = from;
            this.to = to;
        }

        public void start() {
            Timer timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long duration = System.currentTimeMillis() - startTime;
                    double progress = (double) duration / (double) RUN_TIME;
                    if (progress > 1f) {
                        progress = 1f;
                        ((Timer) e.getSource()).stop();
                    }
                    Rectangle target = calculateProgress(from, to, progress);
                    panel.setBounds(target);
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            startTime = System.currentTimeMillis();
            timer.start();
        }

    }

    public Dimension getPreferredSize() {
        return new Dimension(580, 360);
    }

    public static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

        Rectangle bounds = new Rectangle();

        if (startBounds != null && targetBounds != null) {

            bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
            bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

        }

        return bounds;

    }

    public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

        Point point = new Point();

        if (startPoint != null && targetPoint != null) {

            point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
            point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

        }

        return point;

    }

    public static int calculateProgress(int startValue, int endValue, double fraction) {

        int value = 0;
        int distance = endValue - startValue;
        value = (int) Math.round((double) distance * fraction);
        value += startValue;

        return value;

    }

    public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

        Dimension size = new Dimension();

        if (startSize != null && targetSize != null) {

            size.width = calculateProgress(startSize.width, targetSize.width, progress);
            size.height = calculateProgress(startSize.height, targetSize.height, progress);

        }

        return size;

    }

    public void Moving(JPanel PN) {
        Dimension size = this.getSize();
        Rectangle from = new Rectangle(size.width - 300, (size.height - 399), 580, 399);
        Rectangle to = new Rectangle(size.width - 580, (size.height - 399), 580, 399);
        Animate animate = new Animate(PN, from, to);
        animate.start();

    }

    public void Moving2(JPanel PN) {
        Dimension size = this.getSize();
        Rectangle from = new Rectangle(size.width - 350, size.height - 399, 580, 399);
        Rectangle to = new Rectangle(size.width - 578, (size.height - 399), 580, 399);
        Animate animate = new Animate(PN, from, to);
        animate.start();
    }

    public void Moving1(JDesktopPane PN) {
        Dimension size = this.getSize();
        Rectangle from = new Rectangle(size.width - 580 - 300, (size.height - 399), 580, 399);
        Rectangle to = new Rectangle(size.width - 580, (size.height - 399), 580, 399);
        Animate1 animate = new Animate1(PN, from, to);
        animate.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesktopPanel = new javax.swing.JDesktopPane();
        JBTShow = new javax.swing.JButton();
        JBTAdd = new javax.swing.JButton();
        JBTEdit = new javax.swing.JButton();
        JBTDelete = new javax.swing.JButton();
        JBTLogin = new javax.swing.JButton();
        JBTLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        JBTAboutMMS = new javax.swing.JButton();
        JLBWelcome = new javax.swing.JLabel();
        JLBDate = new javax.swing.JLabel();
        JPNShowStudent = new javax.swing.JPanel();
        JPNOptionShowStudent = new javax.swing.JPanel();
        JPNInformationShowStudent = new javax.swing.JPanel();
        JLBStudentIDStudentShowManager = new javax.swing.JLabel();
        JLBStudentID1StudentShowStudent = new javax.swing.JLabel();
        JLBFirstNameStudentShowStudent = new javax.swing.JLabel();
        JLBFirstName1StudentShowStudent = new javax.swing.JLabel();
        JLBLastNameStudentShowManager = new javax.swing.JLabel();
        JLBLastName1StudentShowStudent = new javax.swing.JLabel();
        JLBBirthDayStudentShowStudent = new javax.swing.JLabel();
        JLBBirthDay1StudentShowStudent = new javax.swing.JLabel();
        JLBMobileStudentShowStudent = new javax.swing.JLabel();
        JLBMobile1StudentShowStudent = new javax.swing.JLabel();
        JLBEmailStudentShowStudent = new javax.swing.JLabel();
        JLBEmail1StudentShowStudent = new javax.swing.JLabel();
        JLBAddressStudentShowStudent = new javax.swing.JLabel();
        JLBAddress1StudentShowStudent = new javax.swing.JLabel();
        JLBClassIDStudentShowStudent = new javax.swing.JLabel();
        JLBClassID1StudentShowStudent = new javax.swing.JLabel();
        JLBShowInformaitonShowStudent = new javax.swing.JLabel();
        JPNClassShowStudent = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        JTBClassShowStudent = new javax.swing.JTable();
        JTFClassNameClassShowStudent = new javax.swing.JTextField();
        JLBClassNameClassShowStudent = new javax.swing.JLabel();
        JBTSearchClassShowStudent = new javax.swing.JButton();
        JLBShowClassClassShowStudent = new javax.swing.JLabel();
        JPNOrganizationShowStudent = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        JTBOrganizationShowStudent = new javax.swing.JTable();
        JLBOrganizationNameOrganizationShowStudent = new javax.swing.JLabel();
        JTFOrganizationNameOrganizationShowStudent = new javax.swing.JTextField();
        JBTSearchOrganizationShowStudent = new javax.swing.JButton();
        JLBOrganizationOrganizationShowStudent = new javax.swing.JLabel();
        JPNEventShowStudent = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        JTBEventShosStudent = new javax.swing.JTable();
        JTFEventShowStudent = new javax.swing.JTextField();
        JLBEventNameEventShowStudent = new javax.swing.JLabel();
        JBTSearchEventShowStudent = new javax.swing.JButton();
        JLBEventShowStudent = new javax.swing.JLabel();
        JBTInformationShowStudent = new javax.swing.JButton();
        JBTClassShowStudent = new javax.swing.JButton();
        JBTOrganizationShowStudent = new javax.swing.JButton();
        JBTBackShowStudent = new javax.swing.JButton();
        JBTEventShowStudent = new javax.swing.JButton();
        JPNShowManager = new javax.swing.JPanel();
        JPNOptionShowManager = new javax.swing.JPanel();
        JPNStudentShowManager = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTBStudentShowManager = new javax.swing.JTable();
        JLBNameStudent = new javax.swing.JLabel();
        JTFStudentShowManager = new javax.swing.JTextField();
        JBTSearchStudentShowManager = new javax.swing.JButton();
        JLBSearchStudentSHowManager = new javax.swing.JLabel();
        JPNClassShowManager = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTBClassShowManager = new javax.swing.JTable();
        JLBClassShowManager = new javax.swing.JLabel();
        JTFClassShowManager = new javax.swing.JTextField();
        JBTSearchClassShowManager = new javax.swing.JButton();
        JLBClassShowManager1 = new javax.swing.JLabel();
        JPNOrganizationShowManager = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTBOrganizationShowManager = new javax.swing.JTable();
        JLBOrganizationShowManager = new javax.swing.JLabel();
        JTFOrganizationShowManager = new javax.swing.JTextField();
        JBTSearchOrganizationManager = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        JPNEventShowManager = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        JTBEventShowManager = new javax.swing.JTable();
        JLBEventNameEventShowManager = new javax.swing.JLabel();
        JLNSearchEventSHowManager = new javax.swing.JLabel();
        JTFEventNameEventShowManager = new javax.swing.JTextField();
        JBTSearchEventShowManager = new javax.swing.JButton();
        JPNChartShowManager = new javax.swing.JPanel();
        JBTStudentShowManager = new javax.swing.JButton();
        JBTClassShowManager = new javax.swing.JButton();
        JBTOrganizationShowManager = new javax.swing.JButton();
        JBTBackShowManager = new javax.swing.JButton();
        JBTEventShowManager = new javax.swing.JButton();
        JBTChartShowManager = new javax.swing.JButton();
        JPNAddStudent = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        JTBOrganizationAddStudent = new javax.swing.JTable();
        JLBOrganizationNameAddStudent = new javax.swing.JLabel();
        JTFOrganizationNameAddStudent = new javax.swing.JTextField();
        JBTSearchOrganizationAddStudent = new javax.swing.JButton();
        JDCTimeStartOrganizationAddStudent = new com.toedter.calendar.JDateChooser();
        JDCTimeEndOrganizationAddStudent = new com.toedter.calendar.JDateChooser();
        JLBTimeStartOrganizationAddStudent = new javax.swing.JLabel();
        JLBTimeEndOrganizationAddStudent = new javax.swing.JLabel();
        JBTOrganizationAddStudent = new javax.swing.JButton();
        JBTCancelAddStudent = new javax.swing.JButton();
        JLBRoleOrganizationAddStudent = new javax.swing.JLabel();
        JCBRoleOrganizationAddStudent = new javax.swing.JComboBox();
        JLBAddStudent = new javax.swing.JLabel();
        JPNAddManager = new javax.swing.JPanel();
        JBTStudentAddManager = new javax.swing.JButton();
        JBTClassAddManager = new javax.swing.JButton();
        JBTOrganizationAddManager = new javax.swing.JButton();
        JBTBackAddManager = new javax.swing.JButton();
        JPNOptionAddManager = new javax.swing.JPanel();
        JPNStudentAddManager = new javax.swing.JPanel();
        JTFStudentIDStudentAddManager = new javax.swing.JTextField();
        JTFFirstNameStudentAddManager = new javax.swing.JTextField();
        JTFLastNameStudentAddManager = new javax.swing.JTextField();
        JCBGenderStudentAddManager = new javax.swing.JComboBox();
        JDCBirthDayStudentAddManager = new com.toedter.calendar.JDateChooser();
        JTFMobileStudentAddManager = new javax.swing.JTextField();
        JTFEmailStudentAddManager = new javax.swing.JTextField();
        JTFAddressStudentAddManager = new javax.swing.JTextField();
        JTFClassIDStudentAddManager = new javax.swing.JTextField();
        JTFDescriptionStudentManager = new javax.swing.JTextField();
        JBTAddStudentManager = new javax.swing.JButton();
        JCBStatusStudentAddManager = new javax.swing.JComboBox();
        JLBStudentIDStudentAddManager = new javax.swing.JLabel();
        JLBFirstNameStudentAddManager = new javax.swing.JLabel();
        JLBLastNameStudentAddManager = new javax.swing.JLabel();
        JLBGenderStudentAddManager = new javax.swing.JLabel();
        JLBBirthDayStudentAddManager = new javax.swing.JLabel();
        JLBMobileStudentAddManager = new javax.swing.JLabel();
        JLBEmailStudentAddManager = new javax.swing.JLabel();
        JLBAddressStudentAddManager = new javax.swing.JLabel();
        JLBClassIDStudentAddManager = new javax.swing.JLabel();
        JLBDesCriptionStudentAddManager = new javax.swing.JLabel();
        JLBStatusStudentAddManager = new javax.swing.JLabel();
        JLBAddStudentAddManager = new javax.swing.JLabel();
        JPNClassAddManager = new javax.swing.JPanel();
        JTFClassIDCLassAddManager = new javax.swing.JTextField();
        JTFClassNameClassAddManager = new javax.swing.JTextField();
        JCBYearClassAddManager = new javax.swing.JComboBox();
        JTFMoniterClassAddManager = new javax.swing.JTextField();
        JTFDepartmentIDClassAddManager = new javax.swing.JTextField();
        JBTAddClassAddManager = new javax.swing.JButton();
        JLBClassIDClassAddManager = new javax.swing.JLabel();
        JLBClassNameClassAddManager = new javax.swing.JLabel();
        JLBYearClassAddManager = new javax.swing.JLabel();
        JLBMoniterIDClassAddManager = new javax.swing.JLabel();
        JLBDepartmentIDClassAddManager = new javax.swing.JLabel();
        JLBAddCLassAddManager = new javax.swing.JLabel();
        JPNOrganizationAddManager = new javax.swing.JPanel();
        JTFOrganizationIDOrganizationAddManager = new javax.swing.JTextField();
        JTFOrganizationNameOrganizationAddManager = new javax.swing.JTextField();
        JTFManagerOrganizationAddManager = new javax.swing.JTextField();
        JTFEmailOrganizatonAddManager = new javax.swing.JTextField();
        JTFMobileOrganizationAddManager = new javax.swing.JTextField();
        JBTAddOrganizationAddManager = new javax.swing.JButton();
        JLBOrganizationIDOrganizationAddManager = new javax.swing.JLabel();
        JLBOrganizationNameOrganizationAddManager = new javax.swing.JLabel();
        JLBManagerOrganizationAddManager = new javax.swing.JLabel();
        JLBEmailOrganizationAddManager = new javax.swing.JLabel();
        JLBMobileOrganizationAddManager = new javax.swing.JLabel();
        JLBAddOrganization = new javax.swing.JLabel();
        JPNEventAddManager = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JTFEventIdTextField = new javax.swing.JTextField();
        JTFEventNameTextField = new javax.swing.JTextField();
        JTFEventLocationTextField = new javax.swing.JTextField();
        JDCStartDatePicker = new com.toedter.calendar.JDateChooser();
        JDCEndDatePicker = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        JBTSubmitEvent = new javax.swing.JButton();
        JBTAddEventManagement = new javax.swing.JButton();
        JPNEditStudent = new javax.swing.JPanel();
        JTFFirstNameEditStudent = new javax.swing.JTextField();
        JTFLastNameEditStudent = new javax.swing.JTextField();
        JDCBirthDayEditStudent = new com.toedter.calendar.JDateChooser();
        JTFMobileEditStudent = new javax.swing.JTextField();
        JTFEmailEditStudent = new javax.swing.JTextField();
        JTFAddressEditStudent = new javax.swing.JTextField();
        JBTEditEditStudent = new javax.swing.JButton();
        JBTCancelEditStudent = new javax.swing.JButton();
        JLBFirstNameEditStudent = new javax.swing.JLabel();
        JLBLastNameEditStudent = new javax.swing.JLabel();
        JLBBirthDayEditStudent = new javax.swing.JLabel();
        JLBMobileEditStudent = new javax.swing.JLabel();
        JLBEmailEditStudent = new javax.swing.JLabel();
        JLBAddressEditStudent = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JPNEditManager = new javax.swing.JPanel();
        JBTStudentEditManager = new javax.swing.JButton();
        JBTClassEditManager = new javax.swing.JButton();
        JBTOrganizationEditManager = new javax.swing.JButton();
        JPNOptionEditManager = new javax.swing.JPanel();
        JPNStudentEditStudent = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        JTBStudentEditManager = new javax.swing.JTable(){
            private boolean inLayout;

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return hasExcessWidth();

            }

            @Override
            public void doLayout() {
                if (hasExcessWidth()) {
                    // fool super
                    autoResizeMode = AUTO_RESIZE_SUBSEQUENT_COLUMNS;
                }
                inLayout = true;
                super.doLayout();
                inLayout = false;
                autoResizeMode = AUTO_RESIZE_OFF;
            }

            protected boolean hasExcessWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
                if (isEditing()) {
                    // JW: darn - cleanup to terminate editing ...
                    removeEditor();
                }
                TableColumn resizingColumn = getTableHeader().getResizingColumn();
                // Need to do this here, before the parent's
                // layout manager calls getPreferredSize().
                if (resizingColumn != null && autoResizeMode == AUTO_RESIZE_OFF
                    && !inLayout) {
                    resizingColumn.setPreferredWidth(resizingColumn.getWidth());
                }
                resizeAndRepaint();
            }

        };
        JTFStudentNameStudentEditManager = new javax.swing.JTextField();
        JBTSearchEditStudent = new javax.swing.JButton();
        JBTEditStudentEdit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JPNClassEditClass = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        JTBClassEditClass = new javax.swing.JTable()
        {
            private boolean inLayout;

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return hasExcessWidth();

            }

            @Override
            public void doLayout() {
                if (hasExcessWidth()) {
                    // fool super
                    autoResizeMode = AUTO_RESIZE_SUBSEQUENT_COLUMNS;
                }
                inLayout = true;
                super.doLayout();
                inLayout = false;
                autoResizeMode = AUTO_RESIZE_OFF;
            }

            protected boolean hasExcessWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
                if (isEditing()) {
                    // JW: darn - cleanup to terminate editing ...
                    removeEditor();
                }
                TableColumn resizingColumn = getTableHeader().getResizingColumn();
                // Need to do this here, before the parent's
                // layout manager calls getPreferredSize().
                if (resizingColumn != null && autoResizeMode == AUTO_RESIZE_OFF
                    && !inLayout) {
                    resizingColumn.setPreferredWidth(resizingColumn.getWidth());
                }
                resizeAndRepaint();
            }

        }
        ;
        JTFClassEditClass = new javax.swing.JTextField();
        JBTSearchEditClass = new javax.swing.JButton();
        JBTEditClassEditClass = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        JPNOrganizationEditOrganization = new javax.swing.JPanel();
        JTFOrganizationNameEditOrganization = new javax.swing.JTextField();
        JBTSearchOrganizationEdit = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        JTBOrganizationEditOrganization = new javax.swing.JTable()
        {
            private boolean inLayout;

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return hasExcessWidth();

            }

            @Override
            public void doLayout() {
                if (hasExcessWidth()) {
                    // fool super
                    autoResizeMode = AUTO_RESIZE_SUBSEQUENT_COLUMNS;
                }
                inLayout = true;
                super.doLayout();
                inLayout = false;
                autoResizeMode = AUTO_RESIZE_OFF;
            }

            protected boolean hasExcessWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
                if (isEditing()) {
                    // JW: darn - cleanup to terminate editing ...
                    removeEditor();
                }
                TableColumn resizingColumn = getTableHeader().getResizingColumn();
                // Need to do this here, before the parent's
                // layout manager calls getPreferredSize().
                if (resizingColumn != null && autoResizeMode == AUTO_RESIZE_OFF
                    && !inLayout) {
                    resizingColumn.setPreferredWidth(resizingColumn.getWidth());
                }
                resizeAndRepaint();
            }

        }
        ;
        JBTEditOrganization = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanelEvent = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTableEvent = new javax.swing.JTable();
        JBTApprove = new javax.swing.JButton();
        JBTBackEditManager = new javax.swing.JButton();
        JBTEvent = new javax.swing.JButton();
        JPNDeleteStudent = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        JTBDeleteStudent = new javax.swing.JTable();
        JLBOrganizationNameDeleteStudent = new javax.swing.JLabel();
        JTFOrganizationNameDeleteStudent = new javax.swing.JTextField();
        JBTSearchDeleteStudent = new javax.swing.JButton();
        JBTDeleteDeleteStudent = new javax.swing.JButton();
        JBTUpDeleteStudent = new javax.swing.JButton();
        JBTDownDeleteStudent = new javax.swing.JButton();
        JBTCancelDeleteStudent = new javax.swing.JButton();
        JLBDeleteStudent = new javax.swing.JLabel();
        JPNDeleteManager = new javax.swing.JPanel();
        JPNOptionDeleteManager = new javax.swing.JPanel();
        JPNStudentDeleteManager = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTBStudentDeleteManager = new javax.swing.JTable();
        JLBNameStudentDeleteManager = new javax.swing.JLabel();
        JTFStudentNameStudentDeleteManager = new javax.swing.JTextField();
        JBTSearchStudentDeleteManager = new javax.swing.JButton();
        JBTDeleteStudentDeleteManager = new javax.swing.JButton();
        JBTUpStudentDeleteManager = new javax.swing.JButton();
        JBTDownStudentDeleteManager = new javax.swing.JButton();
        JLBDeleteStudentDeleteManager = new javax.swing.JLabel();
        JPNOrganizationDeleteManager = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTBOrganizationDeleteManager = new javax.swing.JTable();
        JTFOrganizationOrganizationDeleteManager = new javax.swing.JTextField();
        JLBOrganizationNameOrganizationDeleteManager = new javax.swing.JLabel();
        JBTSearchOrganizationDeleteManager = new javax.swing.JButton();
        JBTDeleteOrganizationDeleteManger = new javax.swing.JButton();
        JBTUpOrganizationDeleteManager = new javax.swing.JButton();
        JBTDownOrganizationDeleteManager = new javax.swing.JButton();
        JLBDeleteOrganizationDeleteManager = new javax.swing.JLabel();
        JPNClassDeleteManager = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        JTBClassDeleteManager = new javax.swing.JTable();
        JLBClassNameClassDeleteManager = new javax.swing.JLabel();
        JTFClassClassDeleteManager = new javax.swing.JTextField();
        JBTSearchClassDeleteManager = new javax.swing.JButton();
        JBTDeleteClassDelete = new javax.swing.JButton();
        JBTUpClassDeleteManager = new javax.swing.JButton();
        JBTDownClassDeleteManager = new javax.swing.JButton();
        JLBDeleteClassDeleteManager = new javax.swing.JLabel();
        JBTStudentDeleteManager = new javax.swing.JButton();
        JBTClassDeleteManager = new javax.swing.JButton();
        JBTOrganizationDeleteManager = new javax.swing.JButton();
        JBTBackDeleteManager = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(31, 34, 43));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        DesktopPanel.setBackground(new java.awt.Color(31, 34, 43));
        DesktopPanel.setPreferredSize(new java.awt.Dimension(580, 360));

        JBTShow.setEnabled(false);
        JBTShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTShowActionPerformed(evt);
            }
        });

        JBTAdd.setEnabled(false);
        JBTAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAddActionPerformed(evt);
            }
        });

        JBTEdit.setEnabled(false);
        JBTEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEditActionPerformed(evt);
            }
        });

        JBTDelete.setEnabled(false);
        JBTDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDeleteActionPerformed(evt);
            }
        });

        JBTLogin.setText("Login");
        JBTLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTLoginActionPerformed(evt);
            }
        });

        JBTLogout.setText("Logout");
        JBTLogout.setEnabled(false);
        JBTLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTLogoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Management X9999");

        JBTAboutMMS.setBackground(new java.awt.Color(31, 34, 43));
        JBTAboutMMS.setForeground(new java.awt.Color(255, 255, 255));
        JBTAboutMMS.setText("About SMX");
        JBTAboutMMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAboutMMSActionPerformed(evt);
            }
        });

        JLBWelcome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBWelcome.setForeground(new java.awt.Color(255, 255, 255));

        JLBDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBDate.setForeground(new java.awt.Color(255, 255, 255));

        DesktopPanel.setLayer(JBTShow, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTAdd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTEdit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTLogout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JBTAboutMMS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JLBWelcome, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DesktopPanel.setLayer(JLBDate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopPanelLayout = new javax.swing.GroupLayout(DesktopPanel);
        DesktopPanel.setLayout(DesktopPanelLayout);
        DesktopPanelLayout.setHorizontalGroup(
            DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBTAboutMMS, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(DesktopPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DesktopPanelLayout.createSequentialGroup()
                        .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(DesktopPanelLayout.createSequentialGroup()
                                .addComponent(JBTShow, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBTAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JBTLogin))
                        .addGap(6, 6, 6)
                        .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DesktopPanelLayout.createSequentialGroup()
                                .addComponent(JBTEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JBTDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JBTLogout))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(DesktopPanelLayout.createSequentialGroup()
                        .addComponent(JLBWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLBDate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        DesktopPanelLayout.setVerticalGroup(
            DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopPanelLayout.createSequentialGroup()
                .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DesktopPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBTAboutMMS))
                .addGap(18, 18, 18)
                .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JBTShow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JBTAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBDate, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(JLBWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(DesktopPanel, "card2");

        JPNShowStudent.setBackground(new java.awt.Color(31, 34, 43));
        JPNShowStudent.setPreferredSize(new java.awt.Dimension(616, 360));

        JPNOptionShowStudent.setBackground(new java.awt.Color(31, 34, 43));
        JPNOptionShowStudent.setLayout(new java.awt.CardLayout());

        this.JPNInformationShowStudent.setVisible(false);
        JPNInformationShowStudent.setBackground(new java.awt.Color(31, 34, 43));

        JLBStudentIDStudentShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBStudentIDStudentShowManager.setText("Student ID");

        JLBStudentID1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBFirstNameStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBFirstNameStudentShowStudent.setText("First Name");

        JLBFirstName1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBLastNameStudentShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBLastNameStudentShowManager.setText("Last Name");

        JLBLastName1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBBirthDayStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBBirthDayStudentShowStudent.setText("Birth Day");

        JLBBirthDay1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBMobileStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBMobileStudentShowStudent.setText("Mobile");

        JLBMobile1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBEmailStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBEmailStudentShowStudent.setText("Email");

        JLBEmail1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBAddressStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddressStudentShowStudent.setText("Address");

        JLBAddress1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBClassIDStudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassIDStudentShowStudent.setText("CLass ID");

        JLBClassID1StudentShowStudent.setForeground(new java.awt.Color(255, 255, 255));

        JLBShowInformaitonShowStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBShowInformaitonShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBShowInformaitonShowStudent.setText("Show Information");

        javax.swing.GroupLayout JPNInformationShowStudentLayout = new javax.swing.GroupLayout(JPNInformationShowStudent);
        JPNInformationShowStudent.setLayout(JPNInformationShowStudentLayout);
        JPNInformationShowStudentLayout.setHorizontalGroup(
            JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                        .addComponent(JLBStudentIDStudentShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(JLBStudentID1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                        .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLBFirstNameStudentShowStudent)
                            .addComponent(JLBBirthDayStudentShowStudent)
                            .addComponent(JLBMobileStudentShowStudent)
                            .addComponent(JLBEmailStudentShowStudent)
                            .addComponent(JLBAddressStudentShowStudent)
                            .addComponent(JLBClassIDStudentShowStudent))
                        .addGap(32, 32, 32)
                        .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JLBShowInformaitonShowStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                                        .addComponent(JLBFirstName1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JLBLastNameStudentShowManager))
                                    .addComponent(JLBBirthDay1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBClassID1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBMobile1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBEmail1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBAddress1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLBLastName1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        JPNInformationShowStudentLayout.setVerticalGroup(
            JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(JLBShowInformaitonShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBStudentIDStudentShowManager)
                    .addComponent(JLBStudentID1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(JLBFirstNameStudentShowStudent))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNInformationShowStudentLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLBFirstName1StudentShowStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBLastNameStudentShowManager, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JLBLastName1StudentShowStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBBirthDayStudentShowStudent)
                    .addComponent(JLBBirthDay1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBMobileStudentShowStudent, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JLBMobile1StudentShowStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBEmailStudentShowStudent)
                    .addComponent(JLBEmail1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(JPNInformationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                        .addComponent(JLBAddressStudentShowStudent)
                        .addGap(18, 18, 18)
                        .addComponent(JLBClassIDStudentShowStudent))
                    .addGroup(JPNInformationShowStudentLayout.createSequentialGroup()
                        .addComponent(JLBAddress1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JLBClassID1StudentShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );

        JPNOptionShowStudent.add(JPNInformationShowStudent, "card2");

        JPNClassShowStudent.setBackground(new java.awt.Color(31, 34, 43));

        JTBClassShowStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(JTBClassShowStudent);

        JTFClassNameClassShowStudent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFClassNameClassShowStudentCaretUpdate(evt);
            }
        });

        JLBClassNameClassShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassNameClassShowStudent.setText("Class Name");

        JBTSearchClassShowStudent.setText("Search");
        JBTSearchClassShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchClassShowStudentActionPerformed(evt);
            }
        });

        JLBShowClassClassShowStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBShowClassClassShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBShowClassClassShowStudent.setText("Show Class");

        javax.swing.GroupLayout JPNClassShowStudentLayout = new javax.swing.GroupLayout(JPNClassShowStudent);
        JPNClassShowStudent.setLayout(JPNClassShowStudentLayout);
        JPNClassShowStudentLayout.setHorizontalGroup(
            JPNClassShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassShowStudentLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
            .addGroup(JPNClassShowStudentLayout.createSequentialGroup()
                .addComponent(JLBClassNameClassShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNClassShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNClassShowStudentLayout.createSequentialGroup()
                        .addComponent(JTFClassNameClassShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(JBTSearchClassShowStudent))
                    .addGroup(JPNClassShowStudentLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(JLBShowClassClassShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNClassShowStudentLayout.setVerticalGroup(
            JPNClassShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNClassShowStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBShowClassClassShowStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNClassShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBClassNameClassShowStudent)
                    .addComponent(JTFClassNameClassShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchClassShowStudent))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        JPNOptionShowStudent.add(JPNClassShowStudent, "card3");

        JPNOrganizationShowStudent.setBackground(new java.awt.Color(31, 34, 43));

        JTBOrganizationShowStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(JTBOrganizationShowStudent);

        JLBOrganizationNameOrganizationShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationNameOrganizationShowStudent.setText("OrganizationName");

        JTFOrganizationNameOrganizationShowStudent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationNameOrganizationShowStudentCaretUpdate(evt);
            }
        });

        JBTSearchOrganizationShowStudent.setText("Search");
        JBTSearchOrganizationShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchOrganizationShowStudentActionPerformed(evt);
            }
        });

        JLBOrganizationOrganizationShowStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBOrganizationOrganizationShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationOrganizationShowStudent.setText("Show Organization");

        javax.swing.GroupLayout JPNOrganizationShowStudentLayout = new javax.swing.GroupLayout(JPNOrganizationShowStudent);
        JPNOrganizationShowStudent.setLayout(JPNOrganizationShowStudentLayout);
        JPNOrganizationShowStudentLayout.setHorizontalGroup(
            JPNOrganizationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationShowStudentLayout.createSequentialGroup()
                .addGroup(JPNOrganizationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationShowStudentLayout.createSequentialGroup()
                        .addComponent(JLBOrganizationNameOrganizationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNOrganizationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNOrganizationShowStudentLayout.createSequentialGroup()
                                .addComponent(JTFOrganizationNameOrganizationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(JBTSearchOrganizationShowStudent))
                            .addComponent(JLBOrganizationOrganizationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 73, Short.MAX_VALUE))
        );
        JPNOrganizationShowStudentLayout.setVerticalGroup(
            JPNOrganizationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNOrganizationShowStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBOrganizationOrganizationShowStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNOrganizationShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBOrganizationNameOrganizationShowStudent)
                    .addComponent(JTFOrganizationNameOrganizationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchOrganizationShowStudent))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        JPNOptionShowStudent.add(JPNOrganizationShowStudent, "card4");

        JPNEventShowStudent.setBackground(new java.awt.Color(31, 34, 43));

        JTBEventShosStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane12.setViewportView(JTBEventShosStudent);

        JTFEventShowStudent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFEventShowStudentCaretUpdate(evt);
            }
        });

        JLBEventNameEventShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBEventNameEventShowStudent.setText("Event Name");

        JBTSearchEventShowStudent.setText("Search");
        JBTSearchEventShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchEventShowStudentActionPerformed(evt);
            }
        });

        JLBEventShowStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBEventShowStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBEventShowStudent.setText("Search Event");

        javax.swing.GroupLayout JPNEventShowStudentLayout = new javax.swing.GroupLayout(JPNEventShowStudent);
        JPNEventShowStudent.setLayout(JPNEventShowStudentLayout);
        JPNEventShowStudentLayout.setHorizontalGroup(
            JPNEventShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEventShowStudentLayout.createSequentialGroup()
                .addGroup(JPNEventShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNEventShowStudentLayout.createSequentialGroup()
                        .addComponent(JLBEventNameEventShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTFEventShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNEventShowStudentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JPNEventShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JBTSearchEventShowStudent)
                    .addComponent(JLBEventShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105))
        );
        JPNEventShowStudentLayout.setVerticalGroup(
            JPNEventShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNEventShowStudentLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(JLBEventShowStudent)
                .addGap(18, 18, 18)
                .addGroup(JPNEventShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFEventShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBEventNameEventShowStudent)
                    .addComponent(JBTSearchEventShowStudent))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        JPNOptionShowStudent.add(JPNEventShowStudent, "card5");

        JBTInformationShowStudent.setText("Information");
        JBTInformationShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTInformationShowStudentActionPerformed(evt);
            }
        });

        JBTClassShowStudent.setText("Class");
        JBTClassShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTClassShowStudentActionPerformed(evt);
            }
        });

        JBTOrganizationShowStudent.setText("Organization");
        JBTOrganizationShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationShowStudentActionPerformed(evt);
            }
        });

        JBTBackShowStudent.setText("Back");
        JBTBackShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTBackShowStudentActionPerformed(evt);
            }
        });

        JBTEventShowStudent.setText("Event");
        JBTEventShowStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEventShowStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNShowStudentLayout = new javax.swing.GroupLayout(JPNShowStudent);
        JPNShowStudent.setLayout(JPNShowStudentLayout);
        JPNShowStudentLayout.setHorizontalGroup(
            JPNShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNShowStudentLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(JPNShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JBTBackShowStudent)
                    .addComponent(JBTInformationShowStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTClassShowStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTOrganizationShowStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTEventShowStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addComponent(JPNOptionShowStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNShowStudentLayout.setVerticalGroup(
            JPNShowStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPNOptionShowStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPNShowStudentLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(JBTInformationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBTClassShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBTOrganizationShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBTEventShowStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTBackShowStudent)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(JPNShowStudent, "card3");

        JPNShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JPNShowManager.setPreferredSize(new java.awt.Dimension(580, 360));

        JPNOptionShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNOptionShowManager.setPreferredSize(new java.awt.Dimension(466, 360));
        JPNOptionShowManager.setLayout(new java.awt.CardLayout());

        JPNStudentShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNStudentShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JPNStudentShowManager.setPreferredSize(new java.awt.Dimension(466, 360));

        JTBStudentShowManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(JTBStudentShowManager);

        JLBNameStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBNameStudent.setText("Student Name");

        JTFStudentShowManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFStudentShowManagerCaretUpdate(evt);
            }
        });

        JBTSearchStudentShowManager.setText("Search");
        JBTSearchStudentShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchStudentShowManagerActionPerformed(evt);
            }
        });

        JLBSearchStudentSHowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBSearchStudentSHowManager.setText("Search Student");
        JLBSearchStudentSHowManager.setPreferredSize(new java.awt.Dimension(70, 20));

        javax.swing.GroupLayout JPNStudentShowManagerLayout = new javax.swing.GroupLayout(JPNStudentShowManager);
        JPNStudentShowManager.setLayout(JPNStudentShowManagerLayout);
        JPNStudentShowManagerLayout.setHorizontalGroup(
            JPNStudentShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNStudentShowManagerLayout.createSequentialGroup()
                .addGroup(JPNStudentShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentShowManagerLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(JLBSearchStudentSHowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNStudentShowManagerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JLBNameStudent)
                        .addGap(18, 18, 18)
                        .addComponent(JTFStudentShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(JBTSearchStudentShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        JPNStudentShowManagerLayout.setVerticalGroup(
            JPNStudentShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNStudentShowManagerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(JLBSearchStudentSHowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(JPNStudentShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBNameStudent)
                    .addComponent(JTFStudentShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchStudentShowManager))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        JPNOptionShowManager.add(JPNStudentShowManager, "card2");

        JPNClassShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNClassShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JPNClassShowManager.setPreferredSize(new java.awt.Dimension(430, 360));

        JTBClassShowManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(JTBClassShowManager);

        JLBClassShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassShowManager.setText("Class Name");
        JLBClassShowManager.setPreferredSize(new java.awt.Dimension(55, 15));

        JTFClassShowManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFClassShowManagerCaretUpdate(evt);
            }
        });
        JTFClassShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFClassShowManagerActionPerformed(evt);
            }
        });

        JBTSearchClassShowManager.setText("Search");
        JBTSearchClassShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchClassShowManagerActionPerformed(evt);
            }
        });

        JLBClassShowManager1.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassShowManager1.setText("Search Class");
        JLBClassShowManager1.setPreferredSize(new java.awt.Dimension(70, 20));

        javax.swing.GroupLayout JPNClassShowManagerLayout = new javax.swing.GroupLayout(JPNClassShowManager);
        JPNClassShowManager.setLayout(JPNClassShowManagerLayout);
        JPNClassShowManagerLayout.setHorizontalGroup(
            JPNClassShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassShowManagerLayout.createSequentialGroup()
                .addGroup(JPNClassShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNClassShowManagerLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(JLBClassShowManager1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNClassShowManagerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLBClassShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JTFClassShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(JBTSearchClassShowManager))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNClassShowManagerLayout.setVerticalGroup(
            JPNClassShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNClassShowManagerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(JLBClassShowManager1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPNClassShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBClassShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFClassShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchClassShowManager))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        JPNOptionShowManager.add(JPNClassShowManager, "card3");

        JPNOrganizationShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNOrganizationShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JPNOrganizationShowManager.setPreferredSize(new java.awt.Dimension(430, 360));

        JTBOrganizationShowManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(JTBOrganizationShowManager);

        JLBOrganizationShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationShowManager.setText("Organization Name");

        JTFOrganizationShowManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationShowManagerCaretUpdate(evt);
            }
        });

        JBTSearchOrganizationManager.setText("Search");
        JBTSearchOrganizationManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchOrganizationManagerActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Search Organization");
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 20));

        javax.swing.GroupLayout JPNOrganizationShowManagerLayout = new javax.swing.GroupLayout(JPNOrganizationShowManager);
        JPNOrganizationShowManager.setLayout(JPNOrganizationShowManagerLayout);
        JPNOrganizationShowManagerLayout.setHorizontalGroup(
            JPNOrganizationShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationShowManagerLayout.createSequentialGroup()
                .addGroup(JPNOrganizationShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationShowManagerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLBOrganizationShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTFOrganizationShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(JBTSearchOrganizationManager))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPNOrganizationShowManagerLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        JPNOrganizationShowManagerLayout.setVerticalGroup(
            JPNOrganizationShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNOrganizationShowManagerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNOrganizationShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFOrganizationShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBOrganizationShowManager)
                    .addComponent(JBTSearchOrganizationManager))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        JPNOptionShowManager.add(JPNOrganizationShowManager, "card4");

        JPNEventShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNEventShowManager.setPreferredSize(new java.awt.Dimension(430, 360));

        JTBEventShowManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane11.setViewportView(JTBEventShowManager);

        JLBEventNameEventShowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBEventNameEventShowManager.setText("Event Name");

        JLNSearchEventSHowManager.setForeground(new java.awt.Color(255, 255, 255));
        JLNSearchEventSHowManager.setText("Search Event");
        JLNSearchEventSHowManager.setToolTipText("");

        JTFEventNameEventShowManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFEventNameEventShowManagerCaretUpdate(evt);
            }
        });

        JBTSearchEventShowManager.setText("Search");
        JBTSearchEventShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchEventShowManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNEventShowManagerLayout = new javax.swing.GroupLayout(JPNEventShowManager);
        JPNEventShowManager.setLayout(JPNEventShowManagerLayout);
        JPNEventShowManagerLayout.setHorizontalGroup(
            JPNEventShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEventShowManagerLayout.createSequentialGroup()
                .addGroup(JPNEventShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPNEventShowManagerLayout.createSequentialGroup()
                        .addComponent(JLBEventNameEventShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(JTFEventNameEventShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(JBTSearchEventShowManager)))
                .addGap(0, 43, Short.MAX_VALUE))
            .addGroup(JPNEventShowManagerLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(JLNSearchEventSHowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNEventShowManagerLayout.setVerticalGroup(
            JPNEventShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNEventShowManagerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLNSearchEventSHowManager)
                .addGap(20, 20, 20)
                .addGroup(JPNEventShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBEventNameEventShowManager)
                    .addComponent(JTFEventNameEventShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchEventShowManager))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        JPNOptionShowManager.add(JPNEventShowManager, "card5");

        JPNChartShowManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNChartShowManager.setPreferredSize(new java.awt.Dimension(430, 321));
        JPNChartShowManager.setLayout(new java.awt.BorderLayout());
        JPNOptionShowManager.add(JPNChartShowManager, "card6");

        JBTStudentShowManager.setText("Student");
        JBTStudentShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTStudentShowManagerActionPerformed(evt);
            }
        });

        JBTClassShowManager.setText("Class");
        JBTClassShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTClassShowManagerActionPerformed(evt);
            }
        });

        JBTOrganizationShowManager.setText("Organization");
        JBTOrganizationShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationShowManagerActionPerformed(evt);
            }
        });

        JBTBackShowManager.setText("Back");
        JBTBackShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTBackShowManagerActionPerformed(evt);
            }
        });

        JBTEventShowManager.setText("Event");
        JBTEventShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEventShowManagerActionPerformed(evt);
            }
        });

        JBTChartShowManager.setText("Chart");
        JBTChartShowManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTChartShowManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNShowManagerLayout = new javax.swing.GroupLayout(JPNShowManager);
        JPNShowManager.setLayout(JPNShowManagerLayout);
        JPNShowManagerLayout.setHorizontalGroup(
            JPNShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNShowManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPNShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(JPNShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBTClassShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBTOrganizationShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(JBTBackShowManager))
                    .addComponent(JBTEventShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTChartShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTStudentShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(JPNOptionShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );
        JPNShowManagerLayout.setVerticalGroup(
            JPNShowManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPNOptionShowManager, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
            .addGroup(JPNShowManagerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBTStudentShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTClassShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTOrganizationShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTEventShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTChartShowManager, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTBackShowManager)
                .addGap(54, 54, 54))
        );

        getContentPane().add(JPNShowManager, "card4");

        JPNAddStudent.setBackground(new java.awt.Color(31, 34, 43));
        JPNAddStudent.setPreferredSize(new java.awt.Dimension(564, 360));

        JTBOrganizationAddStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(JTBOrganizationAddStudent);

        JLBOrganizationNameAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationNameAddStudent.setText("Organization Name");

        JTFOrganizationNameAddStudent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationNameAddStudentCaretUpdate(evt);
            }
        });

        JBTSearchOrganizationAddStudent.setText("Search");
        JBTSearchOrganizationAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchOrganizationAddStudentActionPerformed(evt);
            }
        });

        JLBTimeStartOrganizationAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBTimeStartOrganizationAddStudent.setText("Start");

        JLBTimeEndOrganizationAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBTimeEndOrganizationAddStudent.setText("End");

        JBTOrganizationAddStudent.setText("Registry");
        JBTOrganizationAddStudent.setToolTipText("");
        JBTOrganizationAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationAddStudentActionPerformed(evt);
            }
        });

        JBTCancelAddStudent.setText("Cancel");
        JBTCancelAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTCancelAddStudentActionPerformed(evt);
            }
        });

        JLBRoleOrganizationAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBRoleOrganizationAddStudent.setText("Role");

        JCBRoleOrganizationAddStudent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Thanh Vien" }));

        JLBAddStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddStudent.setText("Registry Organization");

        javax.swing.GroupLayout JPNAddStudentLayout = new javax.swing.GroupLayout(JPNAddStudent);
        JPNAddStudent.setLayout(JPNAddStudentLayout);
        JPNAddStudentLayout.setHorizontalGroup(
            JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNAddStudentLayout.createSequentialGroup()
                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNAddStudentLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNAddStudentLayout.createSequentialGroup()
                                .addComponent(JLBOrganizationNameAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JTFOrganizationNameAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(JBTSearchOrganizationAddStudent)
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNAddStudentLayout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLBRoleOrganizationAddStudent)
                                    .addComponent(JLBTimeStartOrganizationAddStudent)
                                    .addComponent(JLBTimeEndOrganizationAddStudent))))
                        .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNAddStudentLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JDCTimeStartOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JCBRoleOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDCTimeEndOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JPNAddStudentLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JBTCancelAddStudent)
                                    .addComponent(JBTOrganizationAddStudent)))))
                    .addGroup(JPNAddStudentLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(JLBAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        JPNAddStudentLayout.setVerticalGroup(
            JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNAddStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBOrganizationNameAddStudent)
                    .addComponent(JTFOrganizationNameAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchOrganizationAddStudent))
                .addGap(18, 18, 18)
                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPNAddStudentLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPNAddStudentLayout.createSequentialGroup()
                                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JLBRoleOrganizationAddStudent)
                                    .addComponent(JCBRoleOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JPNAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JLBTimeStartOrganizationAddStudent)
                                    .addComponent(JDCTimeStartOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JDCTimeEndOrganizationAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7))
                            .addComponent(JLBTimeEndOrganizationAddStudent))
                        .addGap(32, 32, 32)
                        .addComponent(JBTOrganizationAddStudent)
                        .addGap(18, 18, 18)
                        .addComponent(JBTCancelAddStudent)))
                .addGap(27, 27, 27))
        );

        getContentPane().add(JPNAddStudent, "card5");

        JPNAddManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNAddManager.setPreferredSize(new java.awt.Dimension(564, 360));

        JBTStudentAddManager.setText("Student");
        JBTStudentAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTStudentAddManagerActionPerformed(evt);
            }
        });

        JBTClassAddManager.setText("Class");
        JBTClassAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTClassAddManagerActionPerformed(evt);
            }
        });

        JBTOrganizationAddManager.setText("Organization");
        JBTOrganizationAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationAddManagerActionPerformed(evt);
            }
        });

        JBTBackAddManager.setText("Back");
        JBTBackAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTBackAddManagerActionPerformed(evt);
            }
        });

        JPNOptionAddManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNOptionAddManager.setLayout(new java.awt.CardLayout());

        JPNStudentAddManager.setBackground(new java.awt.Color(31, 34, 43));

        JTFStudentIDStudentAddManager.setToolTipText("");
        JTFStudentIDStudentAddManager.setDocument(new LimittedTextField(20));

        this.JTFFirstNameStudentAddManager.setDocument(new LimittedTextField(20));

        this.JTFLastNameStudentAddManager.setDocument(new LimittedTextField(20));

        JCBGenderStudentAddManager.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        this.JTFMobileStudentAddManager.setDocument(new LimittedTextField(15));

        this.JTFEmailStudentAddManager.setDocument(new LimittedTextField(50));

        this.JTFAddressStudentAddManager.setDocument(new LimittedTextField(50));

        this.JTFClassIDStudentAddManager.setDocument(new LimittedTextField(10));

        this.JTFDescriptionStudentManager.setDocument(new LimittedTextField(100));

        JBTAddStudentManager.setText("Add");
        JBTAddStudentManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAddStudentManagerActionPerformed(evt);
            }
        });

        JCBStatusStudentAddManager.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "0" }));

        JLBStudentIDStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBStudentIDStudentAddManager.setText("StudentID");

        JLBFirstNameStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBFirstNameStudentAddManager.setText("FirstName");

        JLBLastNameStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBLastNameStudentAddManager.setText("LastName");

        JLBGenderStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBGenderStudentAddManager.setText("Gender");

        JLBBirthDayStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBBirthDayStudentAddManager.setText("BirthDay");

        JLBMobileStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBMobileStudentAddManager.setText("Mobile");

        JLBEmailStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBEmailStudentAddManager.setText("Email");

        JLBAddressStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddressStudentAddManager.setText("Address");

        JLBClassIDStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassIDStudentAddManager.setText("ClassID");

        JLBDesCriptionStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBDesCriptionStudentAddManager.setText("Description");

        JLBStatusStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBStatusStudentAddManager.setText("Status");

        JLBAddStudentAddManager.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBAddStudentAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddStudentAddManager.setText("Add Student");

        javax.swing.GroupLayout JPNStudentAddManagerLayout = new javax.swing.GroupLayout(JPNStudentAddManager);
        JPNStudentAddManager.setLayout(JPNStudentAddManagerLayout);
        JPNStudentAddManagerLayout.setHorizontalGroup(
            JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBStudentIDStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBFirstNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBGenderStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBMobileStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBEmailStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBAddressStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBDesCriptionStudentAddManager)
                    .addComponent(JLBStatusStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTFDescriptionStudentManager)
                            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                .addComponent(JCBGenderStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(JLBBirthDayStudentAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JLBAddStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                            .addComponent(JTFAddressStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(JLBClassIDStudentAddManager))
                                        .addComponent(JTFStudentIDStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JTFMobileStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JTFEmailStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                            .addComponent(JTFFirstNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(JLBLastNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 12, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                .addComponent(JTFLastNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(28, Short.MAX_VALUE))
                            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                                .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JTFClassIDStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JDCBirthDayStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                        .addComponent(JCBStatusStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(JBTAddStudentManager)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        JPNStudentAddManagerLayout.setVerticalGroup(
            JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JBTAddStudentManager)
                    .addGroup(JPNStudentAddManagerLayout.createSequentialGroup()
                        .addComponent(JLBAddStudentAddManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFStudentIDStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBStudentIDStudentAddManager))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFFirstNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBFirstNameStudentAddManager)
                            .addComponent(JLBLastNameStudentAddManager)
                            .addComponent(JTFLastNameStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(JLBGenderStudentAddManager)
                                .addComponent(JCBGenderStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(JLBBirthDayStudentAddManager))
                            .addComponent(JDCBirthDayStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLBMobileStudentAddManager)
                            .addComponent(JTFMobileStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLBEmailStudentAddManager)
                            .addComponent(JTFEmailStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLBAddressStudentAddManager)
                            .addComponent(JTFAddressStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBClassIDStudentAddManager)
                            .addComponent(JTFClassIDStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLBDesCriptionStudentAddManager)
                            .addComponent(JTFDescriptionStudentManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JPNStudentAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLBStatusStudentAddManager)
                            .addComponent(JCBStatusStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPNOptionAddManager.add(JPNStudentAddManager, "card2");

        JPNClassAddManager.setBackground(new java.awt.Color(31, 34, 43));

        this.JTFClassIDCLassAddManager.setDocument(new LimittedTextField(10));

        this.JTFClassNameClassAddManager.setDocument(new LimittedTextField(20));

        JCBYearClassAddManager.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016", "2017", "2018", "2019", "2020" }));

        this.JTFMoniterClassAddManager.setDocument(new LimittedTextField(20));

        this.JTFDepartmentIDClassAddManager.setDocument(new LimittedTextField(10));

        JBTAddClassAddManager.setText("Add");
        JBTAddClassAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAddClassAddManagerActionPerformed(evt);
            }
        });

        JLBClassIDClassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassIDClassAddManager.setText("ClassID");

        JLBClassNameClassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassNameClassAddManager.setText("CLassName");

        JLBYearClassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBYearClassAddManager.setText("Year");

        JLBMoniterIDClassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBMoniterIDClassAddManager.setText("MoniterID");
        JLBMoniterIDClassAddManager.setToolTipText("");

        JLBDepartmentIDClassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBDepartmentIDClassAddManager.setText("DepartmentID");

        JLBAddCLassAddManager.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBAddCLassAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddCLassAddManager.setText("Add Class");

        javax.swing.GroupLayout JPNClassAddManagerLayout = new javax.swing.GroupLayout(JPNClassAddManager);
        JPNClassAddManager.setLayout(JPNClassAddManagerLayout);
        JPNClassAddManagerLayout.setHorizontalGroup(
            JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassAddManagerLayout.createSequentialGroup()
                .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBClassIDClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBClassNameClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBYearClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBMoniterIDClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBDepartmentIDClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTAddClassAddManager)
                    .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JCBYearClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTFClassIDCLassAddManager)
                        .addComponent(JTFClassNameClassAddManager)
                        .addComponent(JTFMoniterClassAddManager)
                        .addComponent(JTFDepartmentIDClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                    .addComponent(JLBAddCLassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        JPNClassAddManagerLayout.setVerticalGroup(
            JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassAddManagerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(JLBAddCLassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPNClassAddManagerLayout.createSequentialGroup()
                        .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPNClassAddManagerLayout.createSequentialGroup()
                                .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JTFClassIDCLassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBClassIDClassAddManager))
                                .addGap(27, 27, 27)
                                .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JTFClassNameClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLBClassNameClassAddManager))
                                .addGap(18, 18, 18)
                                .addComponent(JCBYearClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JLBYearClassAddManager))
                        .addGap(18, 18, 18)
                        .addGroup(JPNClassAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFMoniterClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBMoniterIDClassAddManager))
                        .addGap(30, 30, 30)
                        .addComponent(JTFDepartmentIDClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JLBDepartmentIDClassAddManager))
                .addGap(18, 18, 18)
                .addComponent(JBTAddClassAddManager)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        JPNOptionAddManager.add(JPNClassAddManager, "card3");

        JPNOrganizationAddManager.setBackground(new java.awt.Color(31, 34, 43));

        this.JTFOrganizationIDOrganizationAddManager.setDocument(new LimittedTextField(10));

        this.JTFOrganizationNameOrganizationAddManager.setDocument(new LimittedTextField(50));

        this.JTFManagerOrganizationAddManager.setDocument(new LimittedTextField(50));

        this.JTFEmailOrganizatonAddManager.setDocument(new LimittedTextField(50));

        this.JTFMobileOrganizationAddManager.setDocument(new LimittedTextField(15));

        JBTAddOrganizationAddManager.setText("Add");
        JBTAddOrganizationAddManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAddOrganizationAddManagerActionPerformed(evt);
            }
        });

        JLBOrganizationIDOrganizationAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationIDOrganizationAddManager.setText("OrganizationID");

        JLBOrganizationNameOrganizationAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationNameOrganizationAddManager.setText("OrganizationName");

        JLBManagerOrganizationAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBManagerOrganizationAddManager.setText("Manager");

        JLBEmailOrganizationAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBEmailOrganizationAddManager.setText("Email");

        JLBMobileOrganizationAddManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBMobileOrganizationAddManager.setText("Mobile");

        JLBAddOrganization.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBAddOrganization.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddOrganization.setText("Add Organization");

        javax.swing.GroupLayout JPNOrganizationAddManagerLayout = new javax.swing.GroupLayout(JPNOrganizationAddManager);
        JPNOrganizationAddManager.setLayout(JPNOrganizationAddManagerLayout);
        JPNOrganizationAddManagerLayout.setHorizontalGroup(
            JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationAddManagerLayout.createSequentialGroup()
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationAddManagerLayout.createSequentialGroup()
                        .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JLBOrganizationNameOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLBManagerOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLBEmailOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLBMobileOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLBOrganizationIDOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTFOrganizationIDOrganizationAddManager)
                            .addComponent(JTFOrganizationNameOrganizationAddManager)
                            .addComponent(JTFManagerOrganizationAddManager)
                            .addComponent(JTFEmailOrganizatonAddManager)
                            .addComponent(JTFMobileOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(JLBAddOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPNOrganizationAddManagerLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(JBTAddOrganizationAddManager)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        JPNOrganizationAddManagerLayout.setVerticalGroup(
            JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationAddManagerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(JLBAddOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFOrganizationIDOrganizationAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBOrganizationIDOrganizationAddManager))
                .addGap(30, 30, 30)
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFOrganizationNameOrganizationAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBOrganizationNameOrganizationAddManager))
                .addGap(33, 33, 33)
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFManagerOrganizationAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBManagerOrganizationAddManager))
                .addGap(26, 26, 26)
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFEmailOrganizatonAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBEmailOrganizationAddManager))
                .addGap(28, 28, 28)
                .addGroup(JPNOrganizationAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFMobileOrganizationAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBMobileOrganizationAddManager))
                .addGap(27, 27, 27)
                .addComponent(JBTAddOrganizationAddManager)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        JPNOptionAddManager.add(JPNOrganizationAddManager, "card4");

        JPNEventAddManager.setBackground(new java.awt.Color(31, 34, 43));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Event Id");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Name");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Location");

        JTFEventIdTextField.setToolTipText("");

        JTFEventNameTextField.setToolTipText("");

        JTFEventLocationTextField.setToolTipText("");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("End Date");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Start Date");

        JBTSubmitEvent.setText("Submit");
        JBTSubmitEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSubmitEventActionPerforment(evt);
            }
        });

        javax.swing.GroupLayout JPNEventAddManagerLayout = new javax.swing.GroupLayout(JPNEventAddManager);
        JPNEventAddManager.setLayout(JPNEventAddManagerLayout);
        JPNEventAddManagerLayout.setHorizontalGroup(
            JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEventAddManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JBTSubmitEvent)
                    .addGroup(JPNEventAddManagerLayout.createSequentialGroup()
                        .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNEventAddManagerLayout.createSequentialGroup()
                                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(JPNEventAddManagerLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(100, 100, 100)))
                        .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTFEventIdTextField)
                            .addComponent(JTFEventNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(JTFEventLocationTextField)
                            .addComponent(JDCStartDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JDCEndDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        JPNEventAddManagerLayout.setVerticalGroup(
            JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEventAddManagerLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFEventIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFEventNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFEventLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDCStartDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPNEventAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDCEndDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JBTSubmitEvent)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        JPNOptionAddManager.add(JPNEventAddManager, "card6");

        JBTAddEventManagement.setText("Event");
        JBTAddEventManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTAddEventManagementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNAddManagerLayout = new javax.swing.GroupLayout(JPNAddManager);
        JPNAddManager.setLayout(JPNAddManagerLayout);
        JPNAddManagerLayout.setHorizontalGroup(
            JPNAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNAddManagerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(JPNAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPNAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JBTOrganizationAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTStudentAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTClassAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTAddEventManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(JBTBackAddManager))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(JPNOptionAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JPNAddManagerLayout.setVerticalGroup(
            JPNAddManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNAddManagerLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(JBTStudentAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTClassAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTOrganizationAddManager, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTAddEventManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTBackAddManager)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(JPNOptionAddManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(JPNAddManager, "card6");

        JPNEditStudent.setBackground(new java.awt.Color(31, 34, 43));
        JPNEditStudent.setPreferredSize(new java.awt.Dimension(559, 360));

        JBTEditEditStudent.setText("Edit");
        JBTEditEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEditEditStudentActionPerformed(evt);
            }
        });

        JBTCancelEditStudent.setText("Cancel");
        JBTCancelEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTCancelEditStudentActionPerformed(evt);
            }
        });

        JLBFirstNameEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBFirstNameEditStudent.setText("First Name");

        JLBLastNameEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBLastNameEditStudent.setText("Last Name");

        JLBBirthDayEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBBirthDayEditStudent.setText("Birth Day");

        JLBMobileEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBMobileEditStudent.setText("Mobile");

        JLBEmailEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBEmailEditStudent.setText("Email");

        JLBAddressEditStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBAddressEditStudent.setText("Address");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Edit Information Student");

        javax.swing.GroupLayout JPNEditStudentLayout = new javax.swing.GroupLayout(JPNEditStudent);
        JPNEditStudent.setLayout(JPNEditStudentLayout);
        JPNEditStudentLayout.setHorizontalGroup(
            JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEditStudentLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBFirstNameEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBBirthDayEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBMobileEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBEmailEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBAddressEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNEditStudentLayout.createSequentialGroup()
                        .addComponent(JTFAddressEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPNEditStudentLayout.createSequentialGroup()
                        .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTFMobileEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTFEmailEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNEditStudentLayout.createSequentialGroup()
                        .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPNEditStudentLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPNEditStudentLayout.createSequentialGroup()
                                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JDCBirthDayEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JTFFirstNameEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JLBLastNameEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(JTFLastNameEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(JPNEditStudentLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(JBTEditEditStudent)
                .addGap(18, 18, 18)
                .addComponent(JBTCancelEditStudent)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNEditStudentLayout.setVerticalGroup(
            JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEditStudentLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPNEditStudentLayout.createSequentialGroup()
                        .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFFirstNameEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTFLastNameEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLBFirstNameEditStudent)
                            .addComponent(JLBLastNameEditStudent))
                        .addGap(18, 18, 18)
                        .addComponent(JDCBirthDayEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JLBBirthDayEditStudent))
                .addGap(30, 30, 30)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFMobileEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBMobileEditStudent))
                .addGap(33, 33, 33)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFEmailEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBEmailEditStudent))
                .addGap(26, 26, 26)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFAddressEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBAddressEditStudent))
                .addGap(18, 18, 18)
                .addGroup(JPNEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTCancelEditStudent)
                    .addComponent(JBTEditEditStudent))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(JPNEditStudent, "card7");

        JPNEditManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNEditManager.setPreferredSize(new java.awt.Dimension(564, 360));

        JBTStudentEditManager.setText("Student");
        JBTStudentEditManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTStudentEditManagerActionPerformed(evt);
            }
        });

        JBTClassEditManager.setText("Class");
        JBTClassEditManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTClassEditManagerActionPerformed(evt);
            }
        });

        JBTOrganizationEditManager.setText("Organization");
        JBTOrganizationEditManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationEditManagerActionPerformed(evt);
            }
        });

        JPNOptionEditManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNOptionEditManager.setLayout(new java.awt.CardLayout());

        JPNStudentEditStudent.setBackground(new java.awt.Color(31, 34, 43));

        JTBStudentEditManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane13.setViewportView(JTBStudentEditManager);

        JTFStudentNameStudentEditManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFStudentNameStudentEditManagerCaretUpdate(evt);
            }
        });

        JBTSearchEditStudent.setText("Search");
        JBTSearchEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchEditStudentActionPerformed(evt);
            }
        });

        JBTEditStudentEdit.setText("Edit");
        JBTEditStudentEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEditStudentEditActionPerformed(evt);
            }
        });

        jButton1.setText("Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Down");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNStudentEditStudentLayout = new javax.swing.GroupLayout(JPNStudentEditStudent);
        JPNStudentEditStudent.setLayout(JPNStudentEditStudentLayout);
        JPNStudentEditStudentLayout.setHorizontalGroup(
            JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                        .addComponent(JBTEditStudentEdit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                        .addGroup(JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(JTFStudentNameStudentEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(JBTSearchEditStudent)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNStudentEditStudentLayout.setVerticalGroup(
            JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNStudentEditStudentLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFStudentNameStudentEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchEditStudent))
                .addGroup(JPNStudentEditStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(JBTEditStudentEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(73, 73, 73))
                    .addGroup(JPNStudentEditStudentLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );

        JPNOptionEditManager.add(JPNStudentEditStudent, "card5");

        JPNClassEditClass.setBackground(new java.awt.Color(31, 34, 43));

        JTBClassEditClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane14.setViewportView(JTBClassEditClass);

        JTFClassEditClass.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFClassEditClassCaretUpdate(evt);
            }
        });

        JBTSearchEditClass.setText("Search");
        JBTSearchEditClass.setToolTipText("");
        JBTSearchEditClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchEditClassActionPerformed(evt);
            }
        });

        JBTEditClassEditClass.setText("Edit");
        JBTEditClassEditClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEditClassEditClassActionPerformed(evt);
            }
        });

        jButton4.setText("Up");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Down");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNClassEditClassLayout = new javax.swing.GroupLayout(JPNClassEditClass);
        JPNClassEditClass.setLayout(JPNClassEditClassLayout);
        JPNClassEditClassLayout.setHorizontalGroup(
            JPNClassEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassEditClassLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(JTFClassEditClass, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(JBTSearchEditClass)
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(JPNClassEditClassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPNClassEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTEditClassEditClass)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );
        JPNClassEditClassLayout.setVerticalGroup(
            JPNClassEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNClassEditClassLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(JPNClassEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFClassEditClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchEditClass))
                .addGroup(JPNClassEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNClassEditClassLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNClassEditClassLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(JBTEditClassEditClass)
                        .addGap(97, 97, 97)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addGap(39, 39, 39))
        );

        JPNOptionEditManager.add(JPNClassEditClass, "card6");

        JPNOrganizationEditOrganization.setBackground(new java.awt.Color(31, 34, 43));

        JTFOrganizationNameEditOrganization.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationNameEditOrganizationCaretUpdate(evt);
            }
        });

        JBTSearchOrganizationEdit.setText("Search");
        JBTSearchOrganizationEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchOrganizationEditActionPerformed(evt);
            }
        });

        JTBOrganizationEditOrganization.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(JTBOrganizationEditOrganization);

        JBTEditOrganization.setText("Edit");
        JBTEditOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEditOrganizationActionPerformed(evt);
            }
        });

        jButton6.setText("Up");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Down");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNOrganizationEditOrganizationLayout = new javax.swing.GroupLayout(JPNOrganizationEditOrganization);
        JPNOrganizationEditOrganization.setLayout(JPNOrganizationEditOrganizationLayout);
        JPNOrganizationEditOrganizationLayout.setHorizontalGroup(
            JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                .addGroup(JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBTEditOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addGroup(JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(JTFOrganizationNameEditOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(JBTSearchOrganizationEdit)
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPNOrganizationEditOrganizationLayout.setVerticalGroup(
            JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTSearchOrganizationEdit)
                    .addComponent(JTFOrganizationNameEditOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(JPNOrganizationEditOrganizationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(JPNOrganizationEditOrganizationLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(JBTEditOrganization)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(57, 57, 57))))
        );

        JPNOptionEditManager.add(JPNOrganizationEditOrganization, "card7");

        jPanelEvent.setBackground(new java.awt.Color(31, 34, 43));

        jTableEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(jTableEvent);

        JBTApprove.setText("Approve");
        JBTApprove.setActionCommand("Approve");
        JBTApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTApproveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEventLayout = new javax.swing.GroupLayout(jPanelEvent);
        jPanelEvent.setLayout(jPanelEventLayout);
        jPanelEventLayout.setHorizontalGroup(
            jPanelEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEventLayout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(JBTApprove)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanelEventLayout.setVerticalGroup(
            jPanelEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEventLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(jPanelEventLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(JBTApprove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPNOptionEditManager.add(jPanelEvent, "card5");

        JBTBackEditManager.setText("Back");
        JBTBackEditManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTBackEditManagerActionPerformed(evt);
            }
        });

        JBTEvent.setText("Event Aprrove");
        JBTEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNEditManagerLayout = new javax.swing.GroupLayout(JPNEditManager);
        JPNEditManager.setLayout(JPNEditManagerLayout);
        JPNEditManagerLayout.setHorizontalGroup(
            JPNEditManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEditManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNEditManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBTOrganizationEditManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTStudentEditManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTClassEditManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTBackEditManager)
                    .addComponent(JBTEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(JPNOptionEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JPNEditManagerLayout.setVerticalGroup(
            JPNEditManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNEditManagerLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(JBTStudentEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTClassEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTOrganizationEditManager, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(JBTEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTBackEditManager)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(JPNOptionEditManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(JPNEditManager, "card8");

        JPNDeleteStudent.setBackground(new java.awt.Color(31, 34, 43));
        JPNDeleteStudent.setPreferredSize(new java.awt.Dimension(564, 360));

        JTBDeleteStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(JTBDeleteStudent);

        JLBOrganizationNameDeleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationNameDeleteStudent.setText("Organization Name");

        JTFOrganizationNameDeleteStudent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationNameDeleteStudentCaretUpdate(evt);
            }
        });

        JBTSearchDeleteStudent.setText("Search");
        JBTSearchDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchDeleteStudentActionPerformed(evt);
            }
        });

        JBTDeleteDeleteStudent.setText("Delete");
        JBTDeleteDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDeleteDeleteStudentActionPerformed(evt);
            }
        });

        JBTUpDeleteStudent.setText("Up");
        JBTUpDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTUpDeleteStudentActionPerformed(evt);
            }
        });

        JBTDownDeleteStudent.setText("Down");
        JBTDownDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDownDeleteStudentActionPerformed(evt);
            }
        });

        JBTCancelDeleteStudent.setText("Cancel");
        JBTCancelDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTCancelDeleteStudentActionPerformed(evt);
            }
        });

        JLBDeleteStudent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBDeleteStudent.setForeground(new java.awt.Color(255, 255, 255));
        JLBDeleteStudent.setText("Delete Participation");

        javax.swing.GroupLayout JPNDeleteStudentLayout = new javax.swing.GroupLayout(JPNDeleteStudent);
        JPNDeleteStudent.setLayout(JPNDeleteStudentLayout);
        JPNDeleteStudentLayout.setHorizontalGroup(
            JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNDeleteStudentLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNDeleteStudentLayout.createSequentialGroup()
                        .addComponent(JLBOrganizationNameDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(JTFOrganizationNameDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(JBTSearchDeleteStudent))
                    .addGroup(JPNDeleteStudentLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBTDeleteDeleteStudent)
                            .addComponent(JBTUpDeleteStudent)
                            .addComponent(JBTDownDeleteStudent)
                            .addComponent(JBTCancelDeleteStudent))))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNDeleteStudentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLBDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );
        JPNDeleteStudentLayout.setVerticalGroup(
            JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNDeleteStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBDeleteStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBOrganizationNameDeleteStudent)
                    .addComponent(JTFOrganizationNameDeleteStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchDeleteStudent))
                .addGroup(JPNDeleteStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPNDeleteStudentLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNDeleteStudentLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(JBTDeleteDeleteStudent)
                        .addGap(53, 53, 53)
                        .addComponent(JBTUpDeleteStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTDownDeleteStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTCancelDeleteStudent)))
                .addGap(40, 40, 40))
        );

        getContentPane().add(JPNDeleteStudent, "card9");

        JPNDeleteManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNDeleteManager.setPreferredSize(new java.awt.Dimension(564, 360));

        JPNOptionDeleteManager.setBackground(new java.awt.Color(31, 34, 43));
        JPNOptionDeleteManager.setLayout(new java.awt.CardLayout());

        JPNStudentDeleteManager.setBackground(new java.awt.Color(31, 34, 43));

        JTBStudentDeleteManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(JTBStudentDeleteManager);

        JLBNameStudentDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBNameStudentDeleteManager.setText("Name");

        this.JTFStudentNameStudentDeleteManager.setDocument(new LimittedTextField(20));
        JTFStudentNameStudentDeleteManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFStudentNameStudentDeleteManagerCaretUpdate(evt);
            }
        });

        JBTSearchStudentDeleteManager.setText("Search");
        JBTSearchStudentDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchStudentDeleteManagerActionPerformed(evt);
            }
        });

        JBTDeleteStudentDeleteManager.setText("Delete");
        JBTDeleteStudentDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDeleteStudentDeleteManagerActionPerformed(evt);
            }
        });

        JBTUpStudentDeleteManager.setText("Up");
        JBTUpStudentDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTUpStudentDeleteManagerActionPerformed(evt);
            }
        });

        JBTDownStudentDeleteManager.setText("Down");
        JBTDownStudentDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDownStudentDeleteManagerActionPerformed(evt);
            }
        });

        JLBDeleteStudentDeleteManager.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBDeleteStudentDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBDeleteStudentDeleteManager.setText("Delete Student");

        javax.swing.GroupLayout JPNStudentDeleteManagerLayout = new javax.swing.GroupLayout(JPNStudentDeleteManager);
        JPNStudentDeleteManager.setLayout(JPNStudentDeleteManagerLayout);
        JPNStudentDeleteManagerLayout.setHorizontalGroup(
            JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                .addGroup(JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                        .addComponent(JLBNameStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTFStudentNameStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(JBTSearchStudentDeleteManager))
                    .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBTDeleteStudentDeleteManager)
                            .addComponent(JBTUpStudentDeleteManager)
                            .addComponent(JBTDownStudentDeleteManager))))
                .addGap(0, 35, Short.MAX_VALUE))
            .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(JLBDeleteStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPNStudentDeleteManagerLayout.setVerticalGroup(
            JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNStudentDeleteManagerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLBDeleteStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLBNameStudentDeleteManager)
                        .addComponent(JTFStudentNameStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBTSearchStudentDeleteManager, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(JPNStudentDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(JBTDeleteStudentDeleteManager)
                        .addGap(76, 76, 76)
                        .addComponent(JBTUpStudentDeleteManager)
                        .addGap(18, 18, 18)
                        .addComponent(JBTDownStudentDeleteManager))
                    .addGroup(JPNStudentDeleteManagerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );

        JPNOptionDeleteManager.add(JPNStudentDeleteManager, "card2");

        JPNOrganizationDeleteManager.setBackground(new java.awt.Color(31, 34, 43));

        JTBOrganizationDeleteManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(JTBOrganizationDeleteManager);

        JTFOrganizationOrganizationDeleteManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFOrganizationOrganizationDeleteManagerCaretUpdate(evt);
            }
        });

        JLBOrganizationNameOrganizationDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBOrganizationNameOrganizationDeleteManager.setText("Organization Name");

        JBTSearchOrganizationDeleteManager.setText("Search");
        JBTSearchOrganizationDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchOrganizationDeleteManagerActionPerformed(evt);
            }
        });

        JBTDeleteOrganizationDeleteManger.setText("Delete");
        JBTDeleteOrganizationDeleteManger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDeleteOrganizationDeleteMangerActionPerformed(evt);
            }
        });

        JBTUpOrganizationDeleteManager.setText("Up");
        JBTUpOrganizationDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTUpOrganizationDeleteManagerActionPerformed(evt);
            }
        });

        JBTDownOrganizationDeleteManager.setText("Down");
        JBTDownOrganizationDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDownOrganizationDeleteManagerActionPerformed(evt);
            }
        });

        JLBDeleteOrganizationDeleteManager.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBDeleteOrganizationDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBDeleteOrganizationDeleteManager.setText("Delete Organization");

        javax.swing.GroupLayout JPNOrganizationDeleteManagerLayout = new javax.swing.GroupLayout(JPNOrganizationDeleteManager);
        JPNOrganizationDeleteManager.setLayout(JPNOrganizationDeleteManagerLayout);
        JPNOrganizationDeleteManagerLayout.setHorizontalGroup(
            JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                .addGroup(JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBTDeleteOrganizationDeleteManger)
                            .addComponent(JBTUpOrganizationDeleteManager)
                            .addComponent(JBTDownOrganizationDeleteManager)))
                    .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(JLBDeleteOrganizationDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                        .addComponent(JLBOrganizationNameOrganizationDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTFOrganizationOrganizationDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTSearchOrganizationDeleteManager)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        JPNOrganizationDeleteManagerLayout.setVerticalGroup(
            JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBDeleteOrganizationDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBOrganizationNameOrganizationDeleteManager)
                    .addComponent(JTFOrganizationOrganizationDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchOrganizationDeleteManager))
                .addGroup(JPNOrganizationDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPNOrganizationDeleteManagerLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(JBTDeleteOrganizationDeleteManger)
                        .addGap(100, 100, 100)
                        .addComponent(JBTUpOrganizationDeleteManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBTDownOrganizationDeleteManager)))
                .addGap(57, 57, 57))
        );

        JPNOptionDeleteManager.add(JPNOrganizationDeleteManager, "card3");

        JPNClassDeleteManager.setBackground(new java.awt.Color(31, 34, 43));

        JTBClassDeleteManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(JTBClassDeleteManager);

        JLBClassNameClassDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBClassNameClassDeleteManager.setText("Class Name");

        JTFClassClassDeleteManager.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JTFClassClassDeleteManagerCaretUpdate(evt);
            }
        });

        JBTSearchClassDeleteManager.setText("Search");
        JBTSearchClassDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTSearchClassDeleteManagerActionPerformed(evt);
            }
        });

        JBTDeleteClassDelete.setText("Delete");
        JBTDeleteClassDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDeleteClassDeleteActionPerformed(evt);
            }
        });

        JBTUpClassDeleteManager.setText("Up");
        JBTUpClassDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTUpClassDeleteManagerActionPerformed(evt);
            }
        });

        JBTDownClassDeleteManager.setText("Down");
        JBTDownClassDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTDownClassDeleteManagerActionPerformed(evt);
            }
        });

        JLBDeleteClassDeleteManager.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JLBDeleteClassDeleteManager.setForeground(new java.awt.Color(255, 255, 255));
        JLBDeleteClassDeleteManager.setText("Delete Class");

        javax.swing.GroupLayout JPNClassDeleteManagerLayout = new javax.swing.GroupLayout(JPNClassDeleteManager);
        JPNClassDeleteManager.setLayout(JPNClassDeleteManagerLayout);
        JPNClassDeleteManagerLayout.setHorizontalGroup(
            JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                .addGroup(JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBTDeleteClassDelete)
                            .addComponent(JBTDownClassDeleteManager)
                            .addComponent(JBTUpClassDeleteManager)))
                    .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                        .addComponent(JLBClassNameClassDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                                .addComponent(JTFClassClassDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JBTSearchClassDeleteManager))
                            .addComponent(JLBDeleteClassDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        JPNClassDeleteManagerLayout.setVerticalGroup(
            JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNClassDeleteManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLBDeleteClassDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBClassNameClassDeleteManager)
                    .addComponent(JTFClassClassDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBTSearchClassDeleteManager))
                .addGroup(JPNClassDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(JPNClassDeleteManagerLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(JBTDeleteClassDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTUpClassDeleteManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTDownClassDeleteManager)
                        .addGap(57, 57, 57))))
        );

        JPNOptionDeleteManager.add(JPNClassDeleteManager, "card4");

        JBTStudentDeleteManager.setText("Student");
        JBTStudentDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTStudentDeleteManagerActionPerformed(evt);
            }
        });

        JBTClassDeleteManager.setText("CLass");
        JBTClassDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTClassDeleteManagerActionPerformed(evt);
            }
        });

        JBTOrganizationDeleteManager.setText("Organization");
        JBTOrganizationDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTOrganizationDeleteManagerActionPerformed(evt);
            }
        });

        JBTBackDeleteManager.setText("Back");
        JBTBackDeleteManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTBackDeleteManagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPNDeleteManagerLayout = new javax.swing.GroupLayout(JPNDeleteManager);
        JPNDeleteManager.setLayout(JPNDeleteManagerLayout);
        JPNDeleteManagerLayout.setHorizontalGroup(
            JPNDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPNDeleteManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPNDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JBTOrganizationDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTClassDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTStudentDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBTBackDeleteManager, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(JPNOptionDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JPNDeleteManagerLayout.setVerticalGroup(
            JPNDeleteManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPNOptionDeleteManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPNDeleteManagerLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(JBTStudentDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTClassDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBTOrganizationDeleteManager, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(JBTBackDeleteManager)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(JPNDeleteManager, "card10");

        setBounds(400, 150, 580, 399);
    }// </editor-fold>//GEN-END:initComponents

    private void initGuiForAccountType(int type) {
        Login.setVisible(false);

        switch (type) {
            case 0:
                this.JBTAdd.setEnabled(false);
                this.JBTDelete.setEnabled(false);
                break;
            default:
                this.JBTAdd.setEnabled(true);
                this.JBTDelete.setEnabled(true);
                break;
        }
        this.JBTShow.setEnabled(true);
        this.JBTEdit.setEnabled(true);
        this.JBTLogin.setEnabled(false);
        this.JBTLogout.setEnabled(true);
    }

    private void JBTLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTLoginActionPerformed
        // TODO add your handling code here:
        Login.setVisible(true);

        if (Login.GetLoginvalue() == true) {
            initGuiForAccountType(this.Login.type);
            this.JLBWelcome.setText("Welcome:" + this.Login.GetName() + "!");
            Log log = new Log();
            try {
                log.insertlog(Login, todayD);
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_JBTLoginActionPerformed

    private void JBTLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTLogoutActionPerformed
        // TODO add your handling code here:
        this.JBTShow.setEnabled(false);
        this.JBTAdd.setEnabled(false);
        this.JBTEdit.setEnabled(false);
        this.JBTDelete.setEnabled(false);
        this.JBTLogin.setEnabled(true);
        this.JBTLogout.setEnabled(false);
        this.Login.LoginValue = false;
        this.JLBWelcome.setText("You are not logged in. Please login!");
    }//GEN-LAST:event_JBTLogoutActionPerformed

    private void JBTShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTShowActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(false);
        switch (this.Login.type) {
            case 0:
                this.JPNShowManager.setVisible(false);
                this.JPNShowStudent.setVisible(true);
                ShowStudent show = new ShowStudent();
                Student student = new Student();
                student = show.ShowInformationStudent(student, Login);
                this.JLBFirstName1StudentShowStudent.setText(student.GetFirstName());
                this.JLBLastName1StudentShowStudent.setText(student.GetLastName());
                this.JLBMobile1StudentShowStudent.setText(student.GetMobile());
                this.JLBEmail1StudentShowStudent.setText(student.GetEmail());
                this.JLBBirthDay1StudentShowStudent.setText(String.valueOf(student.GetBirthDay()));
                this.JLBClassID1StudentShowStudent.setText(student.GetClassID());
                this.JLBAddress1StudentShowStudent.setText(student.GetAddress());
                this.JLBStudentID1StudentShowStudent.setText(student.GetStudentID());
                Moving(this.JPNShowStudent);
                break;

            case 1:
            case 2:
            case 3:
                this.JPNShowManager.setVisible(true);
                this.JPNShowStudent.setVisible(false);
                Moving(this.JPNShowManager);
                break;
        }

    }//GEN-LAST:event_JBTShowActionPerformed

    private void JBTAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAddActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 0:
                this.JPNAddManager.setVisible(false);
                this.JPNAddStudent.setVisible(true);
                Moving(this.JPNAddStudent);
                DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameAddStudent.getText());
                this.JTBOrganizationAddStudent.setModel(DTM);
                if (this.JTBOrganizationAddStudent.getRowCount() >= 0) {
                    this.JTBOrganizationAddStudent.changeSelection(0, 0, false, false);
                }
                break;
            case 1:
                this.JPNAddManager.setVisible(true);
                this.JPNAddStudent.setVisible(false);
                Moving(this.JPNAddManager);
                this.JBTOrganizationAddManager.setVisible(true);
                this.JBTClassAddManager.setVisible(true);
                this.JBTAddEventManagement.setVisible(false);
                this.JPNClassAddManager.setVisible(false);
                this.JPNStudentAddManager.setVisible(true);
                this.JPNOrganizationAddManager.setVisible(false);
                this.JPNEventAddManager.setVisible(false);

                Moving2(this.JPNStudentAddManager);

                break;
            case 3:
                this.JPNAddManager.setVisible(true);
                this.JPNAddStudent.setVisible(false);
                Moving(this.JPNAddManager);
                this.JBTOrganizationAddManager.setVisible(false);
                this.JBTClassAddManager.setVisible(true);
                this.JBTAddEventManagement.setVisible(false);

                this.JPNClassAddManager.setVisible(false);
                this.JPNStudentAddManager.setVisible(true);
                this.JPNOrganizationAddManager.setVisible(false);
                this.JPNEventAddManager.setVisible(false);

                Moving2(this.JPNStudentAddManager);

                break;
            case 2:
                Moving(this.JPNAddManager);
                this.JPNAddManager.setVisible(true);
                this.JPNAddStudent.setVisible(false);
                this.JBTOrganizationAddManager.setVisible(false);
                this.JBTClassAddManager.setVisible(false);
                this.JBTAddEventManagement.setVisible(true);

                this.JPNClassAddManager.setVisible(false);
                this.JPNStudentAddManager.setVisible(false);
                this.JPNOrganizationAddManager.setVisible(false);
                this.JPNEventAddManager.setVisible(true);

                Moving2(this.JPNEventAddManager);
                break;
        }
    }//GEN-LAST:event_JBTAddActionPerformed

    private void JBTEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEditActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(false);
        switch (this.Login.type) {
            //case student
            case 0:
                this.JPNEditManager.setVisible(false);
                this.JPNEditStudent.setVisible(true);
                Moving(this.JPNEditStudent);
                break;
            //case dept
            case 3:
                this.JPNEditManager.setVisible(true);
                this.JPNEditStudent.setVisible(false);
                Moving(this.JPNEditManager);
                this.JBTOrganizationEditManager.setVisible(false);
                this.JBTStudentEditManager.setVisible(true);
                this.JBTClassEditManager.setVisible(true);
                this.JBTEvent.setVisible(false);
                break;
            //case admin
            case 1:
                this.JPNEditManager.setVisible(true);
                this.JPNEditStudent.setVisible(false);
                this.JBTOrganizationEditManager.setVisible(true);
                this.JBTStudentEditManager.setVisible(true);
                this.JBTClassEditManager.setVisible(true);
                this.JBTEvent.setVisible(true);
                Moving(this.JPNEditManager);
                break;
            //case org
            case 2:
                this.JPNEditManager.setVisible(true);
                this.JPNEditStudent.setVisible(false);
                this.JBTClassEditManager.setVisible(false);
                this.JBTStudentEditManager.setVisible(false);
                this.JBTOrganizationEditManager.setVisible(true);
                this.JBTEvent.setVisible(false);
                Moving2(this.JPNOrganizationEditOrganization);
                this.JPNOrganizationEditOrganization.setVisible(true);
                this.JPNClassEditClass.setVisible(false);
                this.JPNStudentEditStudent.setVisible(false);
                DefaultTableModel DTM = new DefaultTableModel();
                ShowManager show = new ShowManager();
                switch (this.Login.type) {
                    case 1:
                        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameEditOrganization.getText());
                        break;
                    case 2:
                        DTM = show.SearchOrganizationBaseOnId(this.Login);
                        break;
                }
                this.JTBOrganizationEditOrganization.setModel(DTM);

                Moving(this.JPNEditManager);
                break;

        }
    }//GEN-LAST:event_JBTEditActionPerformed

    private void JBTDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDeleteActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(false);
        switch (this.Login.type) {
            case 0:
                JLBOrganizationNameDeleteStudent.setText("Organization Name");
                this.JPNDeleteManager.setVisible(false);
                this.JPNDeleteStudent.setVisible(true);
                Moving(this.JPNDeleteStudent);
                DefaultTableModel DTM = new DefaultTableModel();
                DeleteStudent delete = new DeleteStudent();
                DTM = delete.SearchParticipationTextField(this.Login.GetUserName(), this.JTFOrganizationNameDeleteStudent.getText(), Login);
                this.JTBDeleteStudent.setModel(DTM);
                break;
            case 1:
                this.JPNDeleteManager.setVisible(true);
                this.JPNDeleteStudent.setVisible(false);
                Moving(this.JPNDeleteManager);
                JBTOrganizationDeleteManager.setVisible(true);
                break;
            case 2:
                JLBOrganizationNameDeleteStudent.setText("Student Id");
                this.JPNDeleteManager.setVisible(false);
                this.JPNDeleteStudent.setVisible(true);
                Moving(this.JPNDeleteStudent);
                DefaultTableModel orgDTM = new DefaultTableModel();
                DeleteStudent orgDelete = new DeleteStudent();
                orgDTM = orgDelete.searchStudentInOrg(this.Login);
                this.JTBDeleteStudent.setModel(orgDTM);
                break;
            case 3:
                this.JPNDeleteManager.setVisible(true);
                this.JPNDeleteStudent.setVisible(false);
                Moving(this.JPNDeleteManager);
                JBTOrganizationDeleteManager.setVisible(false);
                break;
        }
    }//GEN-LAST:event_JBTDeleteActionPerformed


    private void JTFStudentShowManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFStudentShowManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchStudentTextField(this.Login, this.JTFStudentShowManager.getText());
        this.JTBStudentShowManager.setModel(DTM);
    }//GEN-LAST:event_JTFStudentShowManagerCaretUpdate

    private void JBTSearchStudentShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchStudentShowManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchStudentButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentShowManager.getText());
        this.JTBStudentShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchStudentShowManagerActionPerformed

    private void JTFClassShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFClassShowManagerActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_JTFClassShowManagerActionPerformed

    private void JBTStudentShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTStudentShowManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNStudentShowManager);
        this.JPNStudentShowManager.setVisible(true);
        this.JPNClassShowManager.setVisible(false);
        this.JPNOrganizationShowManager.setVisible(false);
        this.JPNEventShowManager.setVisible(false);
        this.JPNChartShowManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchStudentTextField(this.Login, this.JTFStudentShowManager.getText());
        this.JTBStudentShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTStudentShowManagerActionPerformed

    private void JBTClassShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTClassShowManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNClassShowManager);
        this.JPNClassShowManager.setVisible(true);
        this.JPNStudentShowManager.setVisible(false);
        this.JPNOrganizationShowManager.setVisible(false);
        this.JPNEventShowManager.setVisible(false);
        this.JPNChartShowManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassShowManager.getText());
        this.JTBClassShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTClassShowManagerActionPerformed

    private void JTFClassShowManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFClassShowManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassShowManager.getText());
        this.JTBClassShowManager.setModel(DTM);
    }//GEN-LAST:event_JTFClassShowManagerCaretUpdate

    private void JBTSearchClassShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchClassShowManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassShowManager.getText());
        this.JTBClassShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchClassShowManagerActionPerformed

    private void JTFOrganizationShowManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationShowManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationShowManager.getText());
        this.JTBOrganizationShowManager.setModel(DTM);
    }//GEN-LAST:event_JTFOrganizationShowManagerCaretUpdate

    private void JBTSearchOrganizationManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchOrganizationManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationShowManager.getText());
        this.JTBOrganizationShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchOrganizationManagerActionPerformed

    private void JBTOrganizationShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationShowManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNOrganizationShowManager);
        this.JPNOrganizationShowManager.setVisible(true);
        this.JPNStudentShowManager.setVisible(false);
        this.JPNClassShowManager.setVisible(false);
        this.JPNEventShowManager.setVisible(false);
        this.JPNChartShowManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationShowManager.getText());
        this.JTBOrganizationShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTOrganizationShowManagerActionPerformed

    private void JBTBackShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTBackShowManagerActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNOrganizationShowManager.setVisible(false);
        this.JPNStudentShowManager.setVisible(false);
        this.JPNClassShowManager.setVisible(false);
        this.JPNShowManager.setVisible(false);


    }//GEN-LAST:event_JBTBackShowManagerActionPerformed

    private void JBTAboutMMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAboutMMSActionPerformed
        // TODO add your handling code here:
        this.about.setVisible(true);
    }//GEN-LAST:event_JBTAboutMMSActionPerformed

    private void JBTStudentAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTStudentAddManagerActionPerformed
        // TODO add your handling code here:
        switch (this.Login.type) {
            case 2:
                this.JPNAddManager.setVisible(false);
                this.JPNAddStudent.setVisible(true);
                Moving(this.JPNAddStudent);
                DefaultTableModel DTM = new DefaultTableModel();
                ShowManager show = new ShowManager();
                DTM = show.SearchStudentTextField(this.Login, this.JTFOrganizationNameAddStudent.getText());
                this.JTBOrganizationAddStudent.setModel(DTM);
                if (this.JTBOrganizationAddStudent.getRowCount() >= 0) {
                    this.JTBOrganizationAddStudent.changeSelection(0, 0, false, false);
                }
                break;
            default:
                Moving2(this.JPNStudentAddManager);
                this.JPNStudentAddManager.setVisible(true);
                this.JPNClassAddManager.setVisible(false);
                this.JPNOrganizationAddManager.setVisible(false);
                this.JPNEventAddManager.setVisible(false);
        }
    }//GEN-LAST:event_JBTStudentAddManagerActionPerformed

    private void JBTClassAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTClassAddManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNClassAddManager);
        this.JPNClassAddManager.setVisible(true);
        this.JPNStudentAddManager.setVisible(false);
        this.JPNOrganizationAddManager.setVisible(false);
        this.JPNEventAddManager.setVisible(false);
        switch (this.Login.type) {
            case 3:
                JTFDepartmentIDClassAddManager.setEditable(false);
                JTFDepartmentIDClassAddManager.setText(this.Login.GetUserName());
                break;
            default:
                JTFDepartmentIDClassAddManager.setEditable(true);
                JTFDepartmentIDClassAddManager.setText("");
                break;
        }
    }//GEN-LAST:event_JBTClassAddManagerActionPerformed

    private void JBTOrganizationAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationAddManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNOrganizationAddManager);
        this.JPNOrganizationAddManager.setVisible(true);
        this.JPNStudentAddManager.setVisible(false);
        this.JPNClassAddManager.setVisible(false);
    }//GEN-LAST:event_JBTOrganizationAddManagerActionPerformed

    private void JBTBackAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTBackAddManagerActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNAddManager.setVisible(false);
    }//GEN-LAST:event_JBTBackAddManagerActionPerformed

    private void JBTAddStudentManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAddStudentManagerActionPerformed
        // TODO add your handling code here:
        AddManager add = new AddManager();
        Student student = new Student();
        boolean Sex;
        int Status, Check1, Check2;
        switch (this.Login.type) {
            case 1:
                Check1 = add.CheckClassID(this.JTFClassIDStudentAddManager.getText(), Login);
                break;
            case 3:
                Check1 = add.checkIfClassInDept(this.JTFClassIDStudentAddManager.getText(), Login.GetUserName(), Login);
                break;
            default:
                Check1 = add.CheckClassID(this.JTFClassIDStudentAddManager.getText(), Login);
                break;
        }
        Check2 = add.CheckStudentID(this.JTFStudentIDStudentAddManager.getText(), Login);
        if (Check2 == 1) {
            JOptionPane.showMessageDialog(null, "StudentID  is Incorect!");
        } else {
            if (Check1 == 0) {
                JOptionPane.showMessageDialog(null, "ClassID  is Incorect!");
            } else {
                String vt = (String) this.JCBGenderStudentAddManager.getSelectedItem().toString();;
                Sex = vt.equals("Male");
                student.SetGender(Sex);
                student.SetStudentID(this.JTFStudentIDStudentAddManager.getText());
                student.SetFirstName(this.JTFFirstNameStudentAddManager.getText());
                student.SetLastName(this.JTFLastNameStudentAddManager.getText());
                student.SetBirthDay(convertdate(this.JDCBirthDayStudentAddManager.getDate()));
                student.SetMobile(this.JTFMobileStudentAddManager.getText());
                student.SetEmail(this.JTFEmailStudentAddManager.getText());
                student.SetDescription(this.JTFDescriptionStudentManager.getText());
                student.SetClassID(this.JTFClassIDStudentAddManager.getText());
                vt = (String) this.JCBStatusStudentAddManager.getSelectedItem().toString();
                Status = Integer.parseInt(vt);
                student.SetStatus(Status);
                add.AddStudentManager(student, Login);
                this.JTFStudentIDStudentAddManager.setText("");
                this.JTFFirstNameStudentAddManager.setText("");
                this.JTFLastNameStudentAddManager.setText("");
                this.JDCBirthDayStudentAddManager.setDate(null);
                this.JTFMobileStudentAddManager.setText("");
                this.JTFEmailStudentAddManager.setText("");
                this.JTFClassIDStudentAddManager.setText("");
                this.JTFDescriptionStudentManager.setText("");
                this.JTFAddressStudentAddManager.setText("");
            }
        }
    }//GEN-LAST:event_JBTAddStudentManagerActionPerformed

    private void JBTAddClassAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAddClassAddManagerActionPerformed
        // TODO add your handling code here:
        AddManager add = new AddManager();
        Class cla = new Class();
        int Year, check1, check2, check3;
        check1 = add.CheckClassID(this.JTFClassIDCLassAddManager.getText(), Login);
        check2 = add.CheckDepartmentID(this.JTFDepartmentIDClassAddManager.getText(), Login);
        check3 = add.CheckStudentID(this.JTFMoniterClassAddManager.getText(), Login);
        if (check1 == 1) {
            JOptionPane.showMessageDialog(null, "ClassID is Incorect!");
        } else {
            if (check3 == 0) {
                JOptionPane.showMessageDialog(null, "MoniterID is Incorect!");
            } else {
                if (check2 == 0) {
                    JOptionPane.showMessageDialog(null, "DepartmentID is Incorect!");
                } else {
                    String vt = (String) this.JCBYearClassAddManager.getSelectedItem().toString();
                    Year = Integer.parseInt(vt);
                    cla.SetClassID(this.JTFClassIDCLassAddManager.getText());
                    cla.SetClassName(this.JTFClassNameClassAddManager.getText());
                    cla.SetYear(Year);
                    cla.SetMoniterID(this.JTFMoniterClassAddManager.getText());
                    cla.SetDepartmentID(this.JTFDepartmentIDClassAddManager.getText());
                    add.AddClassManager(cla, Login);
                    this.JTFClassIDCLassAddManager.setText("");
                    this.JTFClassNameClassAddManager.setText("");
                    this.JTFMoniterClassAddManager.setText("");
                    this.JTFDepartmentIDClassAddManager.setText("");
                }
            }
        }

    }//GEN-LAST:event_JBTAddClassAddManagerActionPerformed

    private void JBTAddOrganizationAddManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAddOrganizationAddManagerActionPerformed
        // TODO add your handling code here:
        AddManager add = new AddManager();
        int check = add.CheckOrganizationID(this.JTFOrganizationIDOrganizationAddManager.getText(), Login);
        Organization org = new Organization();
        if (check == 1) {
            JOptionPane.showMessageDialog(null, "OrganizationID is Incorect!");
        } else {
            org.SetOrganizationID(this.JTFOrganizationIDOrganizationAddManager.getText());
            org.SetOrganizationName(this.JTFOrganizationNameOrganizationAddManager.getText());
            org.SetManager(this.JTFManagerOrganizationAddManager.getText());
            org.SetEmail(this.JTFEmailOrganizatonAddManager.getText());
            org.SetMobile(this.JTFMobileOrganizationAddManager.getText());
            add.AddOrganizationManager(org, Login);
            this.JTFOrganizationIDOrganizationAddManager.setText(null);
            this.JTFOrganizationNameOrganizationAddManager.setText(null);
            this.JTFManagerOrganizationAddManager.setText(null);
            this.JTFEmailOrganizatonAddManager.setText(null);
            this.JTFMobileOrganizationAddManager.setText(null);
        }
    }//GEN-LAST:event_JBTAddOrganizationAddManagerActionPerformed

    private void JBTStudentEditManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTStudentEditManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNStudentEditStudent);
        this.JPNStudentEditStudent.setVisible(true);
        //this.JPNStudentEditManager.setVisible(true);
        this.JPNClassEditClass.setVisible(false);
        this.JPNOrganizationEditOrganization.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchStudentTextFieldForEdit(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentEditManager.getText());
                break;
        }
        //DTM=show.SearchStudentTextFieldForEdit(this.Login.GetHostAddress(),this.Login.GetPort(),this.Login.GetDatabaseName(),this.Login.GetUserName(),this.Login.GetPassWord(),this.JTFStudentNameStudentEditManager.getText());
        this.JTBStudentEditManager.setModel(DTM);
    }//GEN-LAST:event_JBTStudentEditManagerActionPerformed

    private void JBTClassEditManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTClassEditManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNClassEditClass);
        this.JPNClassEditClass.setVisible(true);
        this.JPNStudentEditStudent.setVisible(false);
        this.JPNOrganizationEditOrganization.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassEditClass.getText());
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchClassBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassEditClass.getText());
                break;
        }
        this.JTBClassEditClass.setModel(DTM);
    }//GEN-LAST:event_JBTClassEditManagerActionPerformed

    private void JBTOrganizationEditManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationEditManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNOrganizationEditOrganization);
        this.JPNOrganizationEditOrganization.setVisible(true);
        this.JPNClassEditClass.setVisible(false);
        this.JPNStudentEditStudent.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameEditOrganization.getText());
                break;
            case 2:
                DTM = show.SearchOrganizationBaseOnId(this.Login);
                break;
        }
        this.JTBOrganizationEditOrganization.setModel(DTM);
    }//GEN-LAST:event_JBTOrganizationEditManagerActionPerformed

    private void JBTBackEditManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTBackEditManagerActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNEditManager.setVisible(false);
    }//GEN-LAST:event_JBTBackEditManagerActionPerformed

    private void JTFStudentNameStudentDeleteManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFStudentNameStudentDeleteManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchStudentTextField(this.Login, this.JTFStudentNameStudentDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
        }
        this.JTBStudentDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JTFStudentNameStudentDeleteManagerCaretUpdate

    private void JBTSearchStudentDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchStudentDeleteManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchStudentButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
        }
        this.JTBStudentDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchStudentDeleteManagerActionPerformed

    private void JBTStudentDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTStudentDeleteManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNStudentDeleteManager);
        this.JPNStudentDeleteManager.setVisible(true);
        this.JPNOrganizationDeleteManager.setVisible(false);
        this.JPNClassDeleteManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchStudentTextField(this.Login, this.JTFStudentNameStudentDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
        }
        this.JTBStudentDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JBTStudentDeleteManagerActionPerformed

    private void JBTClassDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTClassDeleteManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNClassDeleteManager);
        this.JPNClassDeleteManager.setVisible(true);
        this.JPNStudentDeleteManager.setVisible(false);
        this.JPNOrganizationDeleteManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchClassBaseOnDept(this.Login);
                break;
        }
        this.JTBClassDeleteManager.setModel(DTM);

    }//GEN-LAST:event_JBTClassDeleteManagerActionPerformed

    private void JBTOrganizationDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationDeleteManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNOrganizationDeleteManager);
        this.JPNOrganizationDeleteManager.setVisible(true);
        this.JPNStudentDeleteManager.setVisible(false);
        this.JPNClassDeleteManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationOrganizationDeleteManager.getText());
        this.JTBOrganizationDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JBTOrganizationDeleteManagerActionPerformed

    private void JBTBackDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTBackDeleteManagerActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNDeleteManager.setVisible(false);
    }//GEN-LAST:event_JBTBackDeleteManagerActionPerformed

    private void JBTDeleteStudentDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDeleteStudentDeleteManagerActionPerformed
        // TODO add your handling code here:
        Student student = new Student();
        int n, n1;
        int Row = -1;
        DeleteManager delete = new DeleteManager();
        ShowManager show = new ShowManager();
        Row = this.JTBStudentDeleteManager.getSelectedRow();
        int check = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (Row != -1) {
                String st = (String) this.JTBStudentDeleteManager.getValueAt(Row, 0);
                student.SetStudentID(st);
                n = delete.DeleteStudentManager(student, Login);
                n1 = delete.DeleteParticipationManager(student, Login);
                if (n < 0 || n1 < 0) {
                    JOptionPane.showMessageDialog(this, "Delete false!");
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Success!");
                    DefaultTableModel DTM = new DefaultTableModel();
                    switch (this.Login.type) {
                        case 1:
                            DTM = show.SearchStudentButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentDeleteManager.getText());
                            break;
                        case 3:
                            DTM = show.SearchStudentBaseOnDept(Login);
                            break;
                    }

                    this.JTBStudentDeleteManager.setModel(DTM);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Delete false!");
            }
        }
    }//GEN-LAST:event_JBTDeleteStudentDeleteManagerActionPerformed

    private void JBTUpStudentDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTUpStudentDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBStudentDeleteManager.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBStudentDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBStudentDeleteManager.getRowCount() - 1;
            this.JTBStudentDeleteManager.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_JBTUpStudentDeleteManagerActionPerformed

    private void JBTDownStudentDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDownStudentDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBStudentDeleteManager.getSelectedRow();
        Row += 1;
        if (Row < this.JTBStudentDeleteManager.getRowCount()) {
            this.JTBStudentDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            this.JTBStudentDeleteManager.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_JBTDownStudentDeleteManagerActionPerformed

    private void JTFOrganizationOrganizationDeleteManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationOrganizationDeleteManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationOrganizationDeleteManager.getText());
        this.JTBOrganizationDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JTFOrganizationOrganizationDeleteManagerCaretUpdate

    private void JBTSearchOrganizationDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchOrganizationDeleteManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationOrganizationDeleteManager.getText());
        this.JTBOrganizationDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchOrganizationDeleteManagerActionPerformed

    private void JBTDeleteOrganizationDeleteMangerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDeleteOrganizationDeleteMangerActionPerformed
        // TODO add your handling code here:
        Organization org = new Organization();
        int n, n1;
        int Row = -1;
        DeleteManager delete = new DeleteManager();
        ShowManager show = new ShowManager();
        int check = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            Row = this.JTBOrganizationDeleteManager.getSelectedRow();
            if (Row != -1) {
                String st = (String) this.JTBOrganizationDeleteManager.getValueAt(Row, 0);
                org.SetOrganizationID(st);
                n = delete.DeleteOrganizationManager(org, Login);
                n1 = delete.DeleteParticipation_OrganizationManager(org, Login);
                if (n < 0 || n1 < 0) {
                    JOptionPane.showMessageDialog(this, "Delete false!");
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Success!");
                    DefaultTableModel DTM = new DefaultTableModel();
                    DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationOrganizationDeleteManager.getText());
                    this.JTBOrganizationDeleteManager.setModel(DTM);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Delete false!");
            }
        }
    }//GEN-LAST:event_JBTDeleteOrganizationDeleteMangerActionPerformed

    private void JBTUpOrganizationDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTUpOrganizationDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBOrganizationDeleteManager.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBOrganizationDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBOrganizationDeleteManager.getRowCount() - 1;
            this.JTBOrganizationDeleteManager.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_JBTUpOrganizationDeleteManagerActionPerformed

    private void JBTDownOrganizationDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDownOrganizationDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBOrganizationDeleteManager.getSelectedRow();
        Row += 1;
        if (Row < this.JTBOrganizationDeleteManager.getRowCount()) {
            this.JTBOrganizationDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            this.JTBOrganizationDeleteManager.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_JBTDownOrganizationDeleteManagerActionPerformed

    private void JTFClassClassDeleteManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFClassClassDeleteManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassClassDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchClassBaseOnDept(Login);
                break;

        }
        this.JTBClassDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JTFClassClassDeleteManagerCaretUpdate

    private void JBTSearchClassDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchClassDeleteManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchClassButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassClassDeleteManager.getText());
                break;
            case 3:
                DTM = show.SearchClassBaseOnDept(Login);
                break;

        }
        this.JTBClassDeleteManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchClassDeleteManagerActionPerformed

    private void JBTDeleteClassDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDeleteClassDeleteActionPerformed
        // TODO add your handling code here:
        Class cla = new Class();
        int n, n1;
        int Row = -1;
        DeleteManager delete = new DeleteManager();
        ShowManager show = new ShowManager();
        int check = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            Row = this.JTBClassDeleteManager.getSelectedRow();
            if (Row != -1) {
                String st = (String) this.JTBClassDeleteManager.getValueAt(Row, 0);
                cla.SetClassID(st);
                n = delete.DeleteClassManager(cla, Login);
                n1 = delete.DeleteStudent_ClassManager(cla, Login);
                if (n < 0 || n1 < 0) {
                    JOptionPane.showMessageDialog(this, "Delete false!");
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Success!");
                    DefaultTableModel DTM = new DefaultTableModel();
                    switch (this.Login.type) {
                        case 1:
                            DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassClassDeleteManager.getText());
                            break;
                        case 3:
                            DTM = show.SearchClassBaseOnDept(Login);
                            break;

                    }

                    this.JTBClassDeleteManager.setModel(DTM);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Delete false!");
            }
        }
    }//GEN-LAST:event_JBTDeleteClassDeleteActionPerformed

    private void JBTUpClassDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTUpClassDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBClassDeleteManager.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBClassDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBClassDeleteManager.getRowCount() - 1;
            this.JTBClassDeleteManager.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_JBTUpClassDeleteManagerActionPerformed

    private void JBTDownClassDeleteManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDownClassDeleteManagerActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBClassDeleteManager.getSelectedRow();
        Row += 1;
        if (Row < this.JTBClassDeleteManager.getRowCount()) {
            this.JTBClassDeleteManager.changeSelection(Row, 0, false, false);
        } else {
            this.JTBClassDeleteManager.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_JBTDownClassDeleteManagerActionPerformed

    private void JBTInformationShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTInformationShowStudentActionPerformed
        // TODO add your handling code here:
        ShowStudent show = new ShowStudent();
        Moving2(this.JPNInformationShowStudent);
        this.JPNInformationShowStudent.setVisible(true);
        this.JPNClassShowStudent.setVisible(false);
        this.JPNOrganizationShowStudent.setVisible(false);
        this.JPNEventShowStudent.setVisible(false);
        Student student = new Student();
        student = show.ShowInformationStudent(student, Login);
        this.JLBFirstName1StudentShowStudent.setText(student.GetFirstName());
        this.JLBLastName1StudentShowStudent.setText(student.GetLastName());
        this.JLBMobile1StudentShowStudent.setText(student.GetMobile());
        this.JLBEmail1StudentShowStudent.setText(student.GetEmail());
        this.JLBBirthDay1StudentShowStudent.setText(String.valueOf(student.GetBirthDay()));
        this.JLBClassID1StudentShowStudent.setText(student.GetClassID());
        this.JLBAddress1StudentShowStudent.setText(student.GetAddress());
        this.JLBStudentID1StudentShowStudent.setText(student.GetStudentID());
    }//GEN-LAST:event_JBTInformationShowStudentActionPerformed

    private void JBTClassShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTClassShowStudentActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNClassShowStudent);
        this.JPNClassShowStudent.setVisible(true);
        this.JPNInformationShowStudent.setVisible(false);
        this.JPNOrganizationShowStudent.setVisible(false);
        this.JPNEventShowStudent.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassNameClassShowStudent.getText());
        this.JTBClassShowStudent.setModel(DTM);
    }//GEN-LAST:event_JBTClassShowStudentActionPerformed

    private void JBTOrganizationShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationShowStudentActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNOrganizationShowStudent);
        this.JPNOrganizationShowStudent.setVisible(true);
        this.JPNInformationShowStudent.setVisible(false);
        this.JPNClassShowStudent.setVisible(false);
        this.JPNEventShowStudent.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameOrganizationShowStudent.getText());
        this.JTBOrganizationShowStudent.setModel(DTM);
    }//GEN-LAST:event_JBTOrganizationShowStudentActionPerformed

    private void JBTBackShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTBackShowStudentActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNShowStudent.setVisible(false);

    }//GEN-LAST:event_JBTBackShowStudentActionPerformed

    private void JTFClassNameClassShowStudentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFClassNameClassShowStudentCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassNameClassShowStudent.getText());
        this.JTBClassShowStudent.setModel(DTM);
    }//GEN-LAST:event_JTFClassNameClassShowStudentCaretUpdate

    private void JBTSearchClassShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchClassShowStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchClassButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassNameClassShowStudent.getText());
        this.JTBClassShowStudent.setModel(DTM);
    }//GEN-LAST:event_JBTSearchClassShowStudentActionPerformed

    private void JTFOrganizationNameOrganizationShowStudentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationNameOrganizationShowStudentCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameOrganizationShowStudent.getText());
        this.JTBOrganizationShowStudent.setModel(DTM);
    }//GEN-LAST:event_JTFOrganizationNameOrganizationShowStudentCaretUpdate

    private void JBTSearchOrganizationShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchOrganizationShowStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameOrganizationShowStudent.getText());
        this.JTBOrganizationShowStudent.setModel(DTM);
    }//GEN-LAST:event_JBTSearchOrganizationShowStudentActionPerformed

    private void JTFOrganizationNameAddStudentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationNameAddStudentCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 0:
                DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameAddStudent.getText());
                this.JTBOrganizationAddStudent.setModel(DTM);
                if (this.JTBOrganizationAddStudent.getRowCount() >= 0) {
                    this.JTBOrganizationAddStudent.changeSelection(0, 0, false, false);
                }
                break;
            case 2:
                DTM = show.SearchStudentTextField(this.Login, this.JTFOrganizationNameAddStudent.getText());
                this.JTBOrganizationAddStudent.setModel(DTM);
                if (this.JTBOrganizationAddStudent.getRowCount() >= 0) {
                    this.JTBOrganizationAddStudent.changeSelection(0, 0, false, false);
                }
                break;
        }
    }//GEN-LAST:event_JTFOrganizationNameAddStudentCaretUpdate

    private void JBTSearchOrganizationAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchOrganizationAddStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchOrganizationButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameAddStudent.getText());
        this.JTBOrganizationAddStudent.setModel(DTM);
        if (this.JTBOrganizationAddStudent.getRowCount() >= 0)
            this.JTBOrganizationAddStudent.changeSelection(0, 0, false, false);
    }//GEN-LAST:event_JBTSearchOrganizationAddStudentActionPerformed

    private void JBTOrganizationAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTOrganizationAddStudentActionPerformed
        // TODO add your handling code here:

        String OrgID,
                StudentID,
                Role;
        Role = (String) this.JCBRoleOrganizationAddStudent.getSelectedItem().toString();
        AddStudent Add = new AddStudent();
        Participation_DataLayer Par = new Participation_DataLayer();
        Date Start,
                End;
        int Row = -1;
        int Check;

        switch (this.Login.type) {
            case 0:
                Start = convertdate(this.JDCTimeStartOrganizationAddStudent.getDate());
                End = convertdate(this.JDCTimeEndOrganizationAddStudent.getDate());
                Row = this.JTBOrganizationAddStudent.getSelectedRow();
                if (Row == -1) {
                    JOptionPane.showMessageDialog(this, "Registry falsed!Please try again!");
                } else {
                    OrgID = (String) this.JTBOrganizationAddStudent.getValueAt(Row, 0);
                    StudentID = this.Login.GetUserName();
                    Check = Add.CheckParticipation(StudentID, OrgID, Login);
                    if (Check == 1) {
                        JOptionPane.showMessageDialog(this, "You already joined this organization!");
                    } else {
                        Add.AddParticipation(StudentID, OrgID, Role, Start, End, Login);
                    }
                }
                break;
            case 2:
                Start = convertdate(this.JDCTimeStartOrganizationAddStudent.getDate());
                End = convertdate(this.JDCTimeEndOrganizationAddStudent.getDate());
                Row = this.JTBOrganizationAddStudent.getSelectedRow();
                if (Row == -1) {
                    JOptionPane.showMessageDialog(this, "Registry falsed!Please try again!");
                } else {

                    StudentID = (String) this.JTBOrganizationAddStudent.getValueAt(Row, 0);
                    OrgID = this.Login.GetUserName();
                    Check = Add.CheckParticipation(StudentID, OrgID, Login);
                    if (Check == 1) {
                        JOptionPane.showMessageDialog(this, "this student already joined this organization!");
                    } else {
                        Add.AddParticipation(StudentID, OrgID, Role, Start, End, Login);
                    }
                }
                break;
        }

    }//GEN-LAST:event_JBTOrganizationAddStudentActionPerformed

    private void JBTCancelAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTCancelAddStudentActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNAddStudent.setVisible(false);
    }//GEN-LAST:event_JBTCancelAddStudentActionPerformed

    private void JBTCancelEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTCancelEditStudentActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNEditStudent.setVisible(false);
    }//GEN-LAST:event_JBTCancelEditStudentActionPerformed

    private void JBTEditEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEditEditStudentActionPerformed
        // TODO add your handling code here:
        Student student = new Student();
        EditManager edit = new EditManager();
        student.SetStudentID(Login.GetUserName());
        student.SetFirstName(this.JTFFirstNameEditStudent.getText());
        student.SetLastName(this.JTFLastNameEditStudent.getText());
        student.SetBirthDay(convertdate(this.JDCBirthDayEditStudent.getDate()));
        student.SetMobile(this.JTFMobileEditStudent.getText());
        student.SetEmail(this.JTFEmailEditStudent.getText());
        student.SetAddress(this.JTFAddressEditStudent.getText());
        edit.EditStudentManager(student, Login);
    }//GEN-LAST:event_JBTEditEditStudentActionPerformed

    private void JTFOrganizationNameDeleteStudentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationNameDeleteStudentCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        DeleteStudent delete = new DeleteStudent();
        switch (this.Login.type) {
            case 0:
                DTM = delete.SearchParticipationTextField(this.Login.GetUserName(), this.JTFOrganizationNameDeleteStudent.getText(), Login);
                break;
            case 2:
                DTM = delete.searchStudentInOrg(this.Login);
                break;
        }

        this.JTBDeleteStudent.setModel(DTM);
    }//GEN-LAST:event_JTFOrganizationNameDeleteStudentCaretUpdate

    private void JBTCancelDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTCancelDeleteStudentActionPerformed
        // TODO add your handling code here:
        this.DesktopPanel.setVisible(true);
        Moving1(this.DesktopPanel);
        this.JPNDeleteStudent.setVisible(false);
    }//GEN-LAST:event_JBTCancelDeleteStudentActionPerformed

    private void JBTSearchDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchDeleteStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        DeleteStudent delete = new DeleteStudent();
        switch (this.Login.type) {
            case 0:
                DTM = delete.SearchParticipationButton(this.Login.GetUserName(), this.JTFOrganizationNameDeleteStudent.getText(), Login);
                break;
            case 2:
                DTM = delete.searchStudentInOrg(this.Login);
                break;
        }

        this.JTBDeleteStudent.setModel(DTM);
    }//GEN-LAST:event_JBTSearchDeleteStudentActionPerformed

    private void JBTUpDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTUpDeleteStudentActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBDeleteStudent.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBDeleteStudent.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBDeleteStudent.getRowCount() - 1;
            this.JTBDeleteStudent.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_JBTUpDeleteStudentActionPerformed

    private void JBTDownDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDownDeleteStudentActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBDeleteStudent.getSelectedRow();
        Row += 1;
        if (Row < this.JTBDeleteStudent.getRowCount()) {
            this.JTBDeleteStudent.changeSelection(Row, 0, false, false);
        } else {
            this.JTBDeleteStudent.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_JBTDownDeleteStudentActionPerformed

    private void JBTDeleteDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTDeleteDeleteStudentActionPerformed
        // TODO add your handling code here:
        DeleteStudent delete = new DeleteStudent();
        String stuID;
        String orgID;
        int Row = -1;
        int exe;
        Row = this.JTBDeleteStudent.getSelectedRow();
        int check;
        check = JOptionPane.showConfirmDialog(this, "Are you sure?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            if (Row == -1) {
                JOptionPane.showMessageDialog(this, "Delete false!");
            } else {
                stuID = (String) this.JTBDeleteStudent.getValueAt(Row, 0);
                orgID = (String) this.JTBDeleteStudent.getValueAt(Row, 1);
                exe = delete.DeleteparticipationStudent(stuID, orgID, Login);
                if (exe >= 0) {
                    JOptionPane.showMessageDialog(this, "Delete Success!");
                    DefaultTableModel DTM = new DefaultTableModel();
                    switch (this.Login.type) {
                        case 0:
                            DTM = delete.SearchParticipationTextField(this.Login.GetUserName(), this.JTFOrganizationNameDeleteStudent.getText(), Login);
                            break;
                        case 2:
                            DTM = delete.searchStudentInOrg(this.Login);
                            break;
                    }

                    this.JTBDeleteStudent.setModel(DTM);
                    if (this.JTBDeleteStudent.getRowCount() > 0) {
                        this.JTBDeleteStudent.changeSelection(0, 0, false, false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Delete false!");
                }
            }
    }//GEN-LAST:event_JBTDeleteDeleteStudentActionPerformed
    }
    private void JTFEventNameEventShowManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFEventNameEventShowManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventTextField(this.Login, this.JTFEventNameEventShowManager.getText());
        this.JTBEventShowManager.setModel(DTM);
    }//GEN-LAST:event_JTFEventNameEventShowManagerCaretUpdate

    private void JBTSearchEventShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchEventShowManagerActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventButton(this.Login, this.JTFEventNameEventShowManager.getText());
        this.JTBEventShowManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchEventShowManagerActionPerformed

    private void JBTEventShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEventShowManagerActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNEventShowManager);
        this.JPNEventShowManager.setVisible(true);
        this.JPNOrganizationShowManager.setVisible(false);
        this.JPNStudentShowManager.setVisible(false);
        this.JPNClassShowManager.setVisible(false);
        this.JPNChartShowManager.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventTextField(this.Login, this.JTFEventNameEventShowManager.getText());
        this.JTBEventShowManager.setModel(DTM);

    }//GEN-LAST:event_JBTEventShowManagerActionPerformed

    private void JTFEventShowStudentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFEventShowStudentCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventTextField(this.Login, this.JTFEventShowStudent.getText());
        this.JTBEventShosStudent.setModel(DTM);
    }//GEN-LAST:event_JTFEventShowStudentCaretUpdate

    private void JBTSearchEventShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchEventShowStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventButton(this.Login, this.JTFEventShowStudent.getText());
        this.JTBEventShosStudent.setModel(DTM);
    }//GEN-LAST:event_JBTSearchEventShowStudentActionPerformed

    private void JBTEventShowStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEventShowStudentActionPerformed
        // TODO add your handling code here:
        Moving2(this.JPNEventShowStudent);
        this.JPNEventShowStudent.setVisible(true);
        this.JPNOrganizationShowStudent.setVisible(false);
        this.JPNInformationShowStudent.setVisible(false);
        this.JPNClassShowStudent.setVisible(false);

        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchEventTextField(this.Login, this.JTFEventShowStudent.getText());
        this.JTBEventShosStudent.setModel(DTM);
    }//GEN-LAST:event_JBTEventShowStudentActionPerformed

    private void JBTChartShowManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTChartShowManagerActionPerformed
        // TODO add your handling code here:
        //init chart
        this.JPNChartShowManager.setVisible(true);
        Moving2(this.JPNChartShowManager);
        this.JPNEventShowManager.setVisible(false);
        this.JPNOrganizationShowManager.setVisible(false);
        this.JPNStudentShowManager.setVisible(false);
        this.JPNClassShowManager.setVisible(false);
        int stu, cla, org, event;
        ArrayList<Student> ArrayList1 = new ArrayList<Student>();
        ArrayList1 = SearchAllStudentForChart(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord());
        stu = ArrayList1.size();
        ArrayList<Class> ArrayList2 = new ArrayList<Class>();
        ArrayList2 = SearchAllClassForChart(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord());
        cla = ArrayList2.size();
        ArrayList<Organization> ArrayList3 = new ArrayList<Organization>();
        ArrayList3 = SearchAllOrganizationForChart(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord());
        org = ArrayList3.size();
        ArrayList<Event> ArrayList4 = new ArrayList<Event>();
        ArrayList4 = SearchAllEventForChart(this.Login);
        event = ArrayList4.size();
        DefaultCategoryDataset barCharData = new DefaultCategoryDataset();
        barCharData.setValue(stu, "Chart", "Student");
        barCharData.setValue(cla, "Chart", "Class");
        barCharData.setValue(org, "Chart", "Organization");
        barCharData.setValue(event, "Chart", "Event");
        JFreeChart barChart = ChartFactory.createBarChart("", "OBJECT", "AMOUNT", barCharData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot Chart = barChart.getCategoryPlot();
        Chart.setRangeGridlinePaint(Color.GREEN);
        ChartPanel barpanel = new ChartPanel(barChart);
        this.JPNChartShowManager.removeAll();
        this.JPNChartShowManager.add(barpanel, BorderLayout.CENTER);
        this.JPNChartShowManager.validate();

    }//GEN-LAST:event_JBTChartShowManagerActionPerformed

    private void JTFStudentNameStudentEditManagerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFStudentNameStudentEditManagerCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchStudentTextFieldForEdit(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentEditManager.getText());
                break;
        }
        this.JTBStudentEditManager.setModel(DTM);
    }//GEN-LAST:event_JTFStudentNameStudentEditManagerCaretUpdate

    private void JBTSearchEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchEditStudentActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchStudentBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchStudentButtonForEdit(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentEditManager.getText());
                break;
        }
        this.JTBStudentEditManager.setModel(DTM);
    }//GEN-LAST:event_JBTSearchEditStudentActionPerformed

    private void JBTEditStudentEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEditStudentEditActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBStudentEditManager.getSelectedRow();
        EditStudent Edit = new EditStudent(this, true, JTBStudentEditManager);
        cycleThruChildren(Edit);
        Edit.SetLogin(this.Login);
        Edit.JTFStudentIDEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 0));
        Edit.JTFFirstNameEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 1));
        Edit.JTFLastNameEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 2));
        Edit.JTFMobileEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 5));
        Edit.JTFEmailEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 6));
        Edit.JTFAddressEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 7));
        Edit.JTFClassIDEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 8));
        Edit.JTFDescriptionEditStudent.setText((String) this.JTBStudentEditManager.getValueAt(Row, 9));
        // this.DesktopPanel.add(Edit);
        Edit.setVisible(true);
    }//GEN-LAST:event_JBTEditStudentEditActionPerformed

    private void JTFClassEditClassCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFClassEditClassCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchClassBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchClassTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFStudentNameStudentEditManager.getText());
                break;
        }
        this.JTBClassEditClass.setModel(DTM);
    }//GEN-LAST:event_JTFClassEditClassCaretUpdate

    private void JBTSearchEditClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchEditClassActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 3:
                DTM = show.SearchClassBaseOnDept(this.Login);
                break;
            case 1:
                DTM = show.SearchClassButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFClassEditClass.getText());
                break;
        }
        this.JTBClassEditClass.setModel(DTM);
    }//GEN-LAST:event_JBTSearchEditClassActionPerformed

    private void JBTEditClassEditClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEditClassEditClassActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBClassEditClass.getSelectedRow();
        EditClass cla = new EditClass(this, true, this.JTBClassEditClass);
        cycleThruChildren(cla);
        cla.SetLogin(this.Login);
        cla.JTFClassIDEditClass.setText((String) this.JTBClassEditClass.getValueAt(Row, 0));
        cla.JTFClassnameEditClass.setText((String) this.JTBClassEditClass.getValueAt(Row, 1));
        cla.JTFMoniterIDEditClass.setText((String) this.JTBClassEditClass.getValueAt(Row, 3));
        cla.JTFDepartmentIDEditClass.setText((String) this.JTBClassEditClass.getValueAt(Row, 4));
        cla.setVisible(true);
    }//GEN-LAST:event_JBTEditClassEditClassActionPerformed

    private void JTFOrganizationNameEditOrganizationCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JTFOrganizationNameEditOrganizationCaretUpdate
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchOrganizationTextField(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameEditOrganization.getText());
                break;
            case 2:
                DTM = show.SearchOrganizationBaseOnId(this.Login);
                break;
        }
        this.JTBOrganizationEditOrganization.setModel(DTM);
    }//GEN-LAST:event_JTFOrganizationNameEditOrganizationCaretUpdate

    private void JBTSearchOrganizationEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSearchOrganizationEditActionPerformed
        // TODO add your handling code here:
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        switch (this.Login.type) {
            case 1:
                DTM = show.SearchOrganizationButton(this.Login.GetHostAddress(), this.Login.GetPort(), this.Login.GetDatabaseName(), this.Login.GetUserName(), this.Login.GetPassWord(), this.JTFOrganizationNameEditOrganization.getText());
                break;
            case 2:
                DTM = show.SearchOrganizationBaseOnId(this.Login);
                break;
        }

        this.JTBOrganizationEditOrganization.setModel(DTM);
    }//GEN-LAST:event_JBTSearchOrganizationEditActionPerformed

    private void JBTEditOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEditOrganizationActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBOrganizationEditOrganization.getSelectedRow();
        EditOrganization Edit = new EditOrganization(this, true, JTBOrganizationEditOrganization);
        cycleThruChildren(Edit);
        Edit.SetLogin(this.Login);
        Edit.JTFOrganizationIDEditOrganization.setText((String) this.JTBOrganizationEditOrganization.getValueAt(Row, 0));
        Edit.JTFOrganizationNameEditOrganization.setText((String) this.JTBOrganizationEditOrganization.getValueAt(Row, 1));
        Edit.JTFmanagerEditOrganization.setText((String) this.JTBOrganizationEditOrganization.getValueAt(Row, 2));
        Edit.JTFMobileEditOrganization.setText((String) this.JTBOrganizationEditOrganization.getValueAt(Row, 4));
        Edit.JTFEmailEditOrganziation.setText((String) this.JTBOrganizationEditOrganization.getValueAt(Row, 3));
        Edit.setVisible(true);
    }//GEN-LAST:event_JBTEditOrganizationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBStudentEditManager.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBStudentEditManager.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBStudentEditManager.getRowCount() - 1;
            this.JTBStudentEditManager.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBStudentEditManager.getSelectedRow();
        Row += 1;
        if (Row < this.JTBStudentEditManager.getRowCount()) {
            this.JTBStudentEditManager.changeSelection(Row, 0, false, false);
        } else {
            this.JTBStudentEditManager.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBClassEditClass.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBClassEditClass.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBClassEditClass.getRowCount() - 1;
            this.JTBClassEditClass.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBClassEditClass.getSelectedRow();
        Row += 1;
        if (Row < this.JTBClassEditClass.getRowCount()) {
            this.JTBClassEditClass.changeSelection(Row, 0, false, false);
        } else {
            this.JTBClassEditClass.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBOrganizationEditOrganization.getSelectedRow();
        Row -= 1;
        if (Row >= 0) {
            this.JTBOrganizationEditOrganization.changeSelection(Row, 0, false, false);
        } else {
            int LastRow = this.JTBOrganizationEditOrganization.getRowCount() - 1;
            this.JTBOrganizationEditOrganization.changeSelection(LastRow, 0, false, false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int Row = this.JTBOrganizationEditOrganization.getSelectedRow();
        Row += 1;
        if (Row < this.JTBOrganizationEditOrganization.getRowCount()) {
            this.JTBOrganizationEditOrganization.changeSelection(Row, 0, false, false);
        } else {
            this.JTBOrganizationEditOrganization.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void JBTAddEventManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTAddEventManagementActionPerformed
        // TODO add your handling code here:
        // chechin
        if (Event_DataLayer.canAddEvent(this.Login)) {
                 Moving2(this.JPNEventAddManager);
        this.JPNStudentAddManager.setVisible(false);
        this.JPNClassAddManager.setVisible(false);
        this.JPNOrganizationAddManager.setVisible(false);
        this.JPNEventAddManager.setVisible(true);   
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Can not add new event! Your club is currently having 1 event pending!");
        }
    }//GEN-LAST:event_JBTAddEventManagementActionPerformed

    private void JBTSubmitEventActionPerforment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTSubmitEventActionPerforment
        // TODO add your handling code here:
        if (isValidData()) {
            if (Event_DataLayer.canAddEvent(this.Login)) {
            AddManager add = new AddManager();
            //add event
            Event event = new Event();
            event.SetEventID(JTFEventIdTextField.getText());
            event.SetEventName(JTFEventNameTextField.getText());
            event.SetLocation(JTFEventLocationTextField.getText());
            event.SetTimeStart(convertdate(this.JDCStartDatePicker.getDate()));
            event.SetTimeEnd(convertdate(this.JDCEndDatePicker.getDate()));
            event.setApproved(0);
            event.setOrgId(this.Login.GetUserName());
            add.AddEventManager(event, this.Login);
            JOptionPane.showMessageDialog(this, "Add Event Success!");                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Can not add new event! Your club is currently having 1 event pending!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Add Event Failed!");
        }
    }//GEN-LAST:event_JBTSubmitEventActionPerforment

    private void JBTEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTEventActionPerformed
        // TODO add your handling code here:
        this.jPanelEvent.setVisible(true);
        this.JPNOrganizationEditOrganization.setVisible(false);
        this.JPNClassEditClass.setVisible(false);
        this.JPNStudentEditStudent.setVisible(false);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchNotApproveTable(this.Login);
        this.jTableEvent.setModel(DTM);
    }//GEN-LAST:event_JBTEventActionPerformed

    private void JBTApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTApproveActionPerformed
        // TODO add your handling code here:
        int Row = this.jTableEvent.getSelectedRow();
        String eventId = (String) jTableEvent.getValueAt(Row, 0);
        Event_DataLayer.approve(this.Login, eventId);
        DefaultTableModel DTM = new DefaultTableModel();
        ShowManager show = new ShowManager();
        DTM = show.SearchNotApproveTable(this.Login);
        this.jTableEvent.setModel(DTM);
    }//GEN-LAST:event_JBTApproveActionPerformed

    private Boolean isValidData() {
        return !JTFEventIdTextField.getText().isEmpty()
                && !JTFEventNameTextField.getText().isEmpty()
                && !JTFEventLocationTextField.getText().isEmpty()
                && this.JDCEndDatePicker.getDate() != null
                && this.JDCStartDatePicker.getDate() != null
                && convertdate(this.JDCEndDatePicker.getDate()).after(convertdate(this.JDCStartDatePicker.getDate()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPanel;
    private javax.swing.JButton JBTAboutMMS;
    private javax.swing.JButton JBTAdd;
    private javax.swing.JButton JBTAddClassAddManager;
    private javax.swing.JButton JBTAddEventManagement;
    private javax.swing.JButton JBTAddOrganizationAddManager;
    private javax.swing.JButton JBTAddStudentManager;
    private javax.swing.JButton JBTApprove;
    private javax.swing.JButton JBTBackAddManager;
    private javax.swing.JButton JBTBackDeleteManager;
    private javax.swing.JButton JBTBackEditManager;
    private javax.swing.JButton JBTBackShowManager;
    private javax.swing.JButton JBTBackShowStudent;
    private javax.swing.JButton JBTCancelAddStudent;
    private javax.swing.JButton JBTCancelDeleteStudent;
    private javax.swing.JButton JBTCancelEditStudent;
    private javax.swing.JButton JBTChartShowManager;
    private javax.swing.JButton JBTClassAddManager;
    private javax.swing.JButton JBTClassDeleteManager;
    private javax.swing.JButton JBTClassEditManager;
    private javax.swing.JButton JBTClassShowManager;
    private javax.swing.JButton JBTClassShowStudent;
    private javax.swing.JButton JBTDelete;
    private javax.swing.JButton JBTDeleteClassDelete;
    private javax.swing.JButton JBTDeleteDeleteStudent;
    private javax.swing.JButton JBTDeleteOrganizationDeleteManger;
    private javax.swing.JButton JBTDeleteStudentDeleteManager;
    private javax.swing.JButton JBTDownClassDeleteManager;
    private javax.swing.JButton JBTDownDeleteStudent;
    private javax.swing.JButton JBTDownOrganizationDeleteManager;
    private javax.swing.JButton JBTDownStudentDeleteManager;
    private javax.swing.JButton JBTEdit;
    private javax.swing.JButton JBTEditClassEditClass;
    private javax.swing.JButton JBTEditEditStudent;
    private javax.swing.JButton JBTEditOrganization;
    private javax.swing.JButton JBTEditStudentEdit;
    private javax.swing.JButton JBTEvent;
    private javax.swing.JButton JBTEventShowManager;
    private javax.swing.JButton JBTEventShowStudent;
    private javax.swing.JButton JBTInformationShowStudent;
    private javax.swing.JButton JBTLogin;
    private javax.swing.JButton JBTLogout;
    private javax.swing.JButton JBTOrganizationAddManager;
    private javax.swing.JButton JBTOrganizationAddStudent;
    private javax.swing.JButton JBTOrganizationDeleteManager;
    private javax.swing.JButton JBTOrganizationEditManager;
    private javax.swing.JButton JBTOrganizationShowManager;
    private javax.swing.JButton JBTOrganizationShowStudent;
    private javax.swing.JButton JBTSearchClassDeleteManager;
    private javax.swing.JButton JBTSearchClassShowManager;
    private javax.swing.JButton JBTSearchClassShowStudent;
    private javax.swing.JButton JBTSearchDeleteStudent;
    private javax.swing.JButton JBTSearchEditClass;
    private javax.swing.JButton JBTSearchEditStudent;
    private javax.swing.JButton JBTSearchEventShowManager;
    private javax.swing.JButton JBTSearchEventShowStudent;
    private javax.swing.JButton JBTSearchOrganizationAddStudent;
    private javax.swing.JButton JBTSearchOrganizationDeleteManager;
    private javax.swing.JButton JBTSearchOrganizationEdit;
    private javax.swing.JButton JBTSearchOrganizationManager;
    private javax.swing.JButton JBTSearchOrganizationShowStudent;
    private javax.swing.JButton JBTSearchStudentDeleteManager;
    private javax.swing.JButton JBTSearchStudentShowManager;
    private javax.swing.JButton JBTShow;
    private javax.swing.JButton JBTStudentAddManager;
    private javax.swing.JButton JBTStudentDeleteManager;
    private javax.swing.JButton JBTStudentEditManager;
    private javax.swing.JButton JBTStudentShowManager;
    private javax.swing.JButton JBTSubmitEvent;
    private javax.swing.JButton JBTUpClassDeleteManager;
    private javax.swing.JButton JBTUpDeleteStudent;
    private javax.swing.JButton JBTUpOrganizationDeleteManager;
    private javax.swing.JButton JBTUpStudentDeleteManager;
    private javax.swing.JComboBox JCBGenderStudentAddManager;
    private javax.swing.JComboBox JCBRoleOrganizationAddStudent;
    private javax.swing.JComboBox JCBStatusStudentAddManager;
    private javax.swing.JComboBox JCBYearClassAddManager;
    private com.toedter.calendar.JDateChooser JDCBirthDayEditStudent;
    private com.toedter.calendar.JDateChooser JDCBirthDayStudentAddManager;
    private com.toedter.calendar.JDateChooser JDCEndDatePicker;
    private com.toedter.calendar.JDateChooser JDCStartDatePicker;
    private com.toedter.calendar.JDateChooser JDCTimeEndOrganizationAddStudent;
    private com.toedter.calendar.JDateChooser JDCTimeStartOrganizationAddStudent;
    private javax.swing.JLabel JLBAddCLassAddManager;
    private javax.swing.JLabel JLBAddOrganization;
    private javax.swing.JLabel JLBAddStudent;
    private javax.swing.JLabel JLBAddStudentAddManager;
    private javax.swing.JLabel JLBAddress1StudentShowStudent;
    private javax.swing.JLabel JLBAddressEditStudent;
    private javax.swing.JLabel JLBAddressStudentAddManager;
    private javax.swing.JLabel JLBAddressStudentShowStudent;
    private javax.swing.JLabel JLBBirthDay1StudentShowStudent;
    private javax.swing.JLabel JLBBirthDayEditStudent;
    private javax.swing.JLabel JLBBirthDayStudentAddManager;
    private javax.swing.JLabel JLBBirthDayStudentShowStudent;
    private javax.swing.JLabel JLBClassID1StudentShowStudent;
    private javax.swing.JLabel JLBClassIDClassAddManager;
    private javax.swing.JLabel JLBClassIDStudentAddManager;
    private javax.swing.JLabel JLBClassIDStudentShowStudent;
    private javax.swing.JLabel JLBClassNameClassAddManager;
    private javax.swing.JLabel JLBClassNameClassDeleteManager;
    private javax.swing.JLabel JLBClassNameClassShowStudent;
    private javax.swing.JLabel JLBClassShowManager;
    private javax.swing.JLabel JLBClassShowManager1;
    private javax.swing.JLabel JLBDate;
    private javax.swing.JLabel JLBDeleteClassDeleteManager;
    private javax.swing.JLabel JLBDeleteOrganizationDeleteManager;
    private javax.swing.JLabel JLBDeleteStudent;
    private javax.swing.JLabel JLBDeleteStudentDeleteManager;
    private javax.swing.JLabel JLBDepartmentIDClassAddManager;
    private javax.swing.JLabel JLBDesCriptionStudentAddManager;
    private javax.swing.JLabel JLBEmail1StudentShowStudent;
    private javax.swing.JLabel JLBEmailEditStudent;
    private javax.swing.JLabel JLBEmailOrganizationAddManager;
    private javax.swing.JLabel JLBEmailStudentAddManager;
    private javax.swing.JLabel JLBEmailStudentShowStudent;
    private javax.swing.JLabel JLBEventNameEventShowManager;
    private javax.swing.JLabel JLBEventNameEventShowStudent;
    private javax.swing.JLabel JLBEventShowStudent;
    private javax.swing.JLabel JLBFirstName1StudentShowStudent;
    private javax.swing.JLabel JLBFirstNameEditStudent;
    private javax.swing.JLabel JLBFirstNameStudentAddManager;
    private javax.swing.JLabel JLBFirstNameStudentShowStudent;
    private javax.swing.JLabel JLBGenderStudentAddManager;
    private javax.swing.JLabel JLBLastName1StudentShowStudent;
    private javax.swing.JLabel JLBLastNameEditStudent;
    private javax.swing.JLabel JLBLastNameStudentAddManager;
    private javax.swing.JLabel JLBLastNameStudentShowManager;
    private javax.swing.JLabel JLBManagerOrganizationAddManager;
    private javax.swing.JLabel JLBMobile1StudentShowStudent;
    private javax.swing.JLabel JLBMobileEditStudent;
    private javax.swing.JLabel JLBMobileOrganizationAddManager;
    private javax.swing.JLabel JLBMobileStudentAddManager;
    private javax.swing.JLabel JLBMobileStudentShowStudent;
    private javax.swing.JLabel JLBMoniterIDClassAddManager;
    private javax.swing.JLabel JLBNameStudent;
    private javax.swing.JLabel JLBNameStudentDeleteManager;
    private javax.swing.JLabel JLBOrganizationIDOrganizationAddManager;
    private javax.swing.JLabel JLBOrganizationNameAddStudent;
    private javax.swing.JLabel JLBOrganizationNameDeleteStudent;
    private javax.swing.JLabel JLBOrganizationNameOrganizationAddManager;
    private javax.swing.JLabel JLBOrganizationNameOrganizationDeleteManager;
    private javax.swing.JLabel JLBOrganizationNameOrganizationShowStudent;
    private javax.swing.JLabel JLBOrganizationOrganizationShowStudent;
    private javax.swing.JLabel JLBOrganizationShowManager;
    private javax.swing.JLabel JLBRoleOrganizationAddStudent;
    private javax.swing.JLabel JLBSearchStudentSHowManager;
    private javax.swing.JLabel JLBShowClassClassShowStudent;
    private javax.swing.JLabel JLBShowInformaitonShowStudent;
    private javax.swing.JLabel JLBStatusStudentAddManager;
    private javax.swing.JLabel JLBStudentID1StudentShowStudent;
    private javax.swing.JLabel JLBStudentIDStudentAddManager;
    private javax.swing.JLabel JLBStudentIDStudentShowManager;
    private javax.swing.JLabel JLBTimeEndOrganizationAddStudent;
    private javax.swing.JLabel JLBTimeStartOrganizationAddStudent;
    private javax.swing.JLabel JLBWelcome;
    private javax.swing.JLabel JLBYearClassAddManager;
    private javax.swing.JLabel JLNSearchEventSHowManager;
    private javax.swing.JPanel JPNAddManager;
    private javax.swing.JPanel JPNAddStudent;
    private javax.swing.JPanel JPNChartShowManager;
    private javax.swing.JPanel JPNClassAddManager;
    private javax.swing.JPanel JPNClassDeleteManager;
    private javax.swing.JPanel JPNClassEditClass;
    private javax.swing.JPanel JPNClassShowManager;
    private javax.swing.JPanel JPNClassShowStudent;
    private javax.swing.JPanel JPNDeleteManager;
    private javax.swing.JPanel JPNDeleteStudent;
    private javax.swing.JPanel JPNEditManager;
    private javax.swing.JPanel JPNEditStudent;
    private javax.swing.JPanel JPNEventAddManager;
    private javax.swing.JPanel JPNEventShowManager;
    private javax.swing.JPanel JPNEventShowStudent;
    private javax.swing.JPanel JPNInformationShowStudent;
    private javax.swing.JPanel JPNOptionAddManager;
    private javax.swing.JPanel JPNOptionDeleteManager;
    private javax.swing.JPanel JPNOptionEditManager;
    private javax.swing.JPanel JPNOptionShowManager;
    private javax.swing.JPanel JPNOptionShowStudent;
    private javax.swing.JPanel JPNOrganizationAddManager;
    private javax.swing.JPanel JPNOrganizationDeleteManager;
    private javax.swing.JPanel JPNOrganizationEditOrganization;
    private javax.swing.JPanel JPNOrganizationShowManager;
    private javax.swing.JPanel JPNOrganizationShowStudent;
    private javax.swing.JPanel JPNShowManager;
    private javax.swing.JPanel JPNShowStudent;
    private javax.swing.JPanel JPNStudentAddManager;
    private javax.swing.JPanel JPNStudentDeleteManager;
    private javax.swing.JPanel JPNStudentEditStudent;
    private javax.swing.JPanel JPNStudentShowManager;
    private javax.swing.JTable JTBClassDeleteManager;
    private javax.swing.JTable JTBClassEditClass;
    private javax.swing.JTable JTBClassShowManager;
    private javax.swing.JTable JTBClassShowStudent;
    private javax.swing.JTable JTBDeleteStudent;
    private javax.swing.JTable JTBEventShosStudent;
    private javax.swing.JTable JTBEventShowManager;
    private javax.swing.JTable JTBOrganizationAddStudent;
    private javax.swing.JTable JTBOrganizationDeleteManager;
    private javax.swing.JTable JTBOrganizationEditOrganization;
    private javax.swing.JTable JTBOrganizationShowManager;
    private javax.swing.JTable JTBOrganizationShowStudent;
    private javax.swing.JTable JTBStudentDeleteManager;
    private javax.swing.JTable JTBStudentEditManager;
    private javax.swing.JTable JTBStudentShowManager;
    private javax.swing.JTextField JTFAddressEditStudent;
    private javax.swing.JTextField JTFAddressStudentAddManager;
    private javax.swing.JTextField JTFClassClassDeleteManager;
    private javax.swing.JTextField JTFClassEditClass;
    private javax.swing.JTextField JTFClassIDCLassAddManager;
    private javax.swing.JTextField JTFClassIDStudentAddManager;
    private javax.swing.JTextField JTFClassNameClassAddManager;
    private javax.swing.JTextField JTFClassNameClassShowStudent;
    private javax.swing.JTextField JTFClassShowManager;
    private javax.swing.JTextField JTFDepartmentIDClassAddManager;
    private javax.swing.JTextField JTFDescriptionStudentManager;
    private javax.swing.JTextField JTFEmailEditStudent;
    private javax.swing.JTextField JTFEmailOrganizatonAddManager;
    private javax.swing.JTextField JTFEmailStudentAddManager;
    private javax.swing.JTextField JTFEventIdTextField;
    private javax.swing.JTextField JTFEventLocationTextField;
    private javax.swing.JTextField JTFEventNameEventShowManager;
    private javax.swing.JTextField JTFEventNameTextField;
    private javax.swing.JTextField JTFEventShowStudent;
    private javax.swing.JTextField JTFFirstNameEditStudent;
    private javax.swing.JTextField JTFFirstNameStudentAddManager;
    private javax.swing.JTextField JTFLastNameEditStudent;
    private javax.swing.JTextField JTFLastNameStudentAddManager;
    private javax.swing.JTextField JTFManagerOrganizationAddManager;
    private javax.swing.JTextField JTFMobileEditStudent;
    private javax.swing.JTextField JTFMobileOrganizationAddManager;
    private javax.swing.JTextField JTFMobileStudentAddManager;
    private javax.swing.JTextField JTFMoniterClassAddManager;
    private javax.swing.JTextField JTFOrganizationIDOrganizationAddManager;
    private javax.swing.JTextField JTFOrganizationNameAddStudent;
    private javax.swing.JTextField JTFOrganizationNameDeleteStudent;
    private javax.swing.JTextField JTFOrganizationNameEditOrganization;
    private javax.swing.JTextField JTFOrganizationNameOrganizationAddManager;
    private javax.swing.JTextField JTFOrganizationNameOrganizationShowStudent;
    private javax.swing.JTextField JTFOrganizationOrganizationDeleteManager;
    private javax.swing.JTextField JTFOrganizationShowManager;
    private javax.swing.JTextField JTFStudentIDStudentAddManager;
    private javax.swing.JTextField JTFStudentNameStudentDeleteManager;
    private javax.swing.JTextField JTFStudentNameStudentEditManager;
    private javax.swing.JTextField JTFStudentShowManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelEvent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableEvent;
    // End of variables declaration//GEN-END:variables

}
