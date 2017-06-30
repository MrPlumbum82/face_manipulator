package facemanipulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 * Creates a frame with a graphics component inside it.
 * @author Sadan Mallhi
 * @version Apr 23, 2014
 * 
 * I certify that I wrote all of the code in this file myself.
 */
public class FaceManipulatorComponent extends JComponent {

    public Color bgColor = Color.BLACK;
    public Color headColor = Color.YELLOW;
    public Color eyeColor = Color.BLACK;
    int EYE_RADIUS = 30;
    int SPACE = 20;
    int BROW_THICKNESS = 5;
    int MOUTH_THICKNESS = 10;
    int w;
    int h;
    int face = 3;
    int brows = 3;
    int pimple = 0;
    int glass = 0;

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
        repaint();
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
        repaint();
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
        repaint();
    }

    public int getEYE_RADIUS() {
        return EYE_RADIUS;
    }

    public void setEYE_RADIUS(int EYE_RADIUS) {
        this.EYE_RADIUS = EYE_RADIUS;
        repaint();
    }

    public int getSPACE() {
        return SPACE;
    }

    public void setSPACE(int SPACE) {
        this.SPACE = SPACE;
        repaint();
    }

    public int getBROW_THICKNESS() {
        return BROW_THICKNESS;
    }

    public void setBROW_THICKNESS(int BROW_THICKNESS) {
        this.BROW_THICKNESS = BROW_THICKNESS;
        repaint();
    }

    public int getMOUTH_THICKNESS() {
        return MOUTH_THICKNESS;
    }

    public void setMOUTH_THICKNESS(int MOUTH_THICKNESS) {
        this.MOUTH_THICKNESS = MOUTH_THICKNESS;
        repaint();
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
        repaint();
    }

    public int getBrows() {
        return brows;
    }

    public void setBrows(int brows) {
        this.brows = brows;
        repaint();
    }

    public int getPimple() {
        return pimple;
    }

    public void setPimple(int pimple) {
        this.pimple = pimple;
        repaint();
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
        repaint();
    }

    /**
     * Draws a face with a black background
     *
     * @param g the current graphics content
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        w = getWidth();
        h = getHeight();

        // paint background
        g2.setColor(bgColor);
        g2.fill(new Rectangle(w, h));

        //antialiasing
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // move the origin to center
        g2.translate(w / 2, h / 2);

        //SPACE = 20; // padding between head and edge of frame
        // head
        Ellipse2D.Double head = new Ellipse2D.Double(-w / 2 + SPACE, -h / 2
                + SPACE, w - 2 * SPACE, h - 2 * SPACE);
        g2.setColor(headColor);
        g2.fill(head);

        g2.setColor(eyeColor);
        //EYE_RADIUS = Math.min(w / 15, h / 15);

        // left eye
        int leftEyeCenterX = -w / 5;
        int leftEyeCenterY = -h / 5;
        Ellipse2D.Double leftEye = new Ellipse2D.Double(leftEyeCenterX
                - EYE_RADIUS, leftEyeCenterY - EYE_RADIUS, 2 * EYE_RADIUS, 2
                * EYE_RADIUS);
        g2.fill(leftEye);

        // right eye
        int rightEyeCenterX = w / 5;
        int rightEyeCenterY = -h / 5;
        Ellipse2D.Double rightEye = new Ellipse2D.Double(rightEyeCenterX
                - EYE_RADIUS, rightEyeCenterY - EYE_RADIUS, 2 * EYE_RADIUS, 2
                * EYE_RADIUS);
        g2.fill(rightEye);

        g2.setColor(Color.BLACK);

        if (brows == 1) {

            // normal leftbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int leftLeftBrow = leftEyeCenterX - EYE_RADIUS;
            int leftRightBrow = leftEyeCenterX + EYE_RADIUS;
            Point2D.Double leftLeftBrowEnd = new Point2D.Double(leftLeftBrow,
                    leftEyeCenterY - (EYE_RADIUS));
            Point2D.Double leftRightBrowEnd = new Point2D.Double(leftRightBrow,
                    rightEyeCenterY - EYE_RADIUS);
            Line2D.Double brow = new Line2D.Double(leftLeftBrowEnd,
                    leftRightBrowEnd);
            g2.draw(brow);

            // normal rightbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int rightLeftBrow = rightEyeCenterX - EYE_RADIUS;
            int rightRightBrow = rightEyeCenterX + EYE_RADIUS;
            Point2D.Double rightLeftBrowEnd = new Point2D.Double(rightLeftBrow,
                    rightEyeCenterY - EYE_RADIUS);
            Point2D.Double rightRightBrowEnd = new Point2D.Double(rightRightBrow,
                    rightEyeCenterY - (EYE_RADIUS));
            Line2D.Double rbrow = new Line2D.Double(rightLeftBrowEnd,
                    rightRightBrowEnd);
            g2.draw(rbrow);
        }

        if (brows == 2) {
            // sad leftbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int leftLeftBrow = leftEyeCenterX - EYE_RADIUS;
            int leftRightBrow = leftEyeCenterX + EYE_RADIUS;
            Point2D.Double leftLeftBrowEnd = new Point2D.Double(leftLeftBrow,
                    leftEyeCenterY - (EYE_RADIUS));
            Point2D.Double leftRightBrowEnd = new Point2D.Double(leftRightBrow,
                    rightEyeCenterY - (EYE_RADIUS + 30));
            Line2D.Double brow = new Line2D.Double(leftLeftBrowEnd,
                    leftRightBrowEnd);
            g2.draw(brow);

            // sad rightbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int rightLeftBrow = rightEyeCenterX - EYE_RADIUS;
            int rightRightBrow = rightEyeCenterX + EYE_RADIUS;
            Point2D.Double rightLeftBrowEnd = new Point2D.Double(rightLeftBrow,
                    rightEyeCenterY - (EYE_RADIUS + 30));
            Point2D.Double rightRightBrowEnd = new Point2D.Double(rightRightBrow,
                    rightEyeCenterY - (EYE_RADIUS));
            Line2D.Double rbrow = new Line2D.Double(rightLeftBrowEnd,
                    rightRightBrowEnd);
            g2.draw(rbrow);
        }

        if (brows == 3) {
            // angry leftbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int leftLeftBrow = leftEyeCenterX - EYE_RADIUS;
            int leftRightBrow = leftEyeCenterX + EYE_RADIUS;
            Point2D.Double leftLeftBrowEnd = new Point2D.Double(leftLeftBrow,
                    leftEyeCenterY - (EYE_RADIUS + 30));
            Point2D.Double leftRightBrowEnd = new Point2D.Double(leftRightBrow,
                    rightEyeCenterY - EYE_RADIUS);
            Line2D.Double brow = new Line2D.Double(leftLeftBrowEnd,
                    leftRightBrowEnd);
            g2.draw(brow);

            // angry rightbrow
            g2.setStroke(new BasicStroke(BROW_THICKNESS));
            int rightLeftBrow = rightEyeCenterX - EYE_RADIUS;
            int rightRightBrow = rightEyeCenterX + EYE_RADIUS;
            Point2D.Double rightLeftBrowEnd = new Point2D.Double(rightLeftBrow,
                    rightEyeCenterY - EYE_RADIUS);
            Point2D.Double rightRightBrowEnd = new Point2D.Double(rightRightBrow,
                    rightEyeCenterY - (EYE_RADIUS + 30));
            Line2D.Double rbrow = new Line2D.Double(rightLeftBrowEnd,
                    rightRightBrowEnd);
            g2.draw(rbrow);
        }

        int leftMouth = leftEyeCenterX;
        int rightMouth = rightEyeCenterX;

        if (face == 1) {

            // straight mouth
            g2.setStroke(new BasicStroke(MOUTH_THICKNESS));
            Point2D.Double leftEnd = new Point2D.Double(leftMouth, h / 5);
            Point2D.Double rightEnd = new Point2D.Double(rightMouth, h / 5);
            Line2D.Double mouth = new Line2D.Double(leftEnd, rightEnd);
            g2.draw(mouth);

        }

        if (face == 2) {

            // slanting mouth
            g2.setStroke(new BasicStroke(MOUTH_THICKNESS));
            Point2D.Double leftEnd = new Point2D.Double(leftMouth, h / 4);
            Point2D.Double rightEnd = new Point2D.Double(rightMouth, h / 5);
            Line2D.Double mouth = new Line2D.Double(leftEnd, rightEnd);
            g2.draw(mouth);
        }

        if (face == 3) {

            // happy face
            g2.setStroke(new BasicStroke(MOUTH_THICKNESS));
            Point2D.Double leftEnd = new Point2D.Double(leftMouth, h / 5);
            Point2D.Double rightEnd = new Point2D.Double(rightMouth, h / 5);
            Line2D.Double mouth = new Line2D.Double(leftEnd, rightEnd);
            g2.draw(mouth);
            g2.drawArc(leftMouth, h / 9, rightMouth * 2, 100, 180, 180);

        }

        if (pimple == 1) {
            g2.setColor(Color.RED);
            Ellipse2D.Double zit1 = new Ellipse2D.Double(155, 55, 5, 5);
            Ellipse2D.Double zit2 = new Ellipse2D.Double(165, 75, 5, 5);
            Ellipse2D.Double zit3 = new Ellipse2D.Double(175, 65, 5, 5);
            Ellipse2D.Double zit4 = new Ellipse2D.Double(-165, 55, 5, 5);
            Ellipse2D.Double zit5 = new Ellipse2D.Double(-175, 65, 5, 5);
            Ellipse2D.Double zit6 = new Ellipse2D.Double(-155, 75, 5, 5);
            Ellipse2D.Double zit7 = new Ellipse2D.Double(25, -125, 5, 5);
            Ellipse2D.Double zit8 = new Ellipse2D.Double(55, -135, 5, 5);
            Ellipse2D.Double zit9 = new Ellipse2D.Double(-55, -145, 5, 5);
            g2.fill(zit1);
            g2.fill(zit2);
            g2.fill(zit3);
            g2.fill(zit4);
            g2.fill(zit5);
            g2.fill(zit6);
            g2.fill(zit7);
            g2.fill(zit8);
            g2.fill(zit9);

        }

        if (glass == 1) {
            g2.setColor(Color.BLACK);
            Ellipse2D.Double LeftGlass = new Ellipse2D.Double(leftEyeCenterX
                    - (EYE_RADIUS * 3 / 2), leftEyeCenterY - (EYE_RADIUS * 3
                    / 2), EYE_RADIUS * 3, EYE_RADIUS * 3);
            Ellipse2D.Double RightGlass = new Ellipse2D.Double(rightEyeCenterX
                    - (EYE_RADIUS * 3 / 2), rightEyeCenterY - (EYE_RADIUS * 3
                    / 2), EYE_RADIUS * 3, EYE_RADIUS * 3);
            Point2D.Double leftEnd = new Point2D.Double(leftEyeCenterX + 50,
                    leftEyeCenterY);
            Point2D.Double rightEnd = new Point2D.Double(rightEyeCenterX - 50,
                    rightEyeCenterY);
            Line2D.Double mouth = new Line2D.Double(leftEnd, rightEnd);
            g2.draw(mouth);
            g2.draw(LeftGlass);
            g2.draw(RightGlass);
        }

    }
}
