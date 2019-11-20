package utils;

import java.awt.Component;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {

    public ImageRenderer(ImageIcon icon) {
        this.icon = icon;
    }

    JLabel lbl = new JLabel("", SwingConstants.CENTER);

    private ImageIcon icon;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        lbl.setText((String) value);
        lbl.setIcon(icon);
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return lbl;
    }

}
