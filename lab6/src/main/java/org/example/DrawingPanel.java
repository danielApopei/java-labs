package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * the actual playing area
 */
public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    int lastX = -10, lastY = -10;
    ArrayList<Stone> stones = new ArrayList<>();
    ArrayList<Road> roads = new ArrayList<>();
    Color currentPlayer = Color.RED;
    BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = bufferedImage.createGraphics();
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
    }

    boolean isRoadBetween(Point a, Point b) {
        for(Road road : roads) {
            if(a.getX() == road.x1 && a.getY() == road.y1 && b.getX() == road.x2 && b.getY() == road.y2) return true;
            if(a.getX() == road.x2 && a.getY() == road.y2 && b.getX() == road.x1 && b.getY() == road.y1) return true;
        }
        return false;
    }
    final void init(int rows, int cols) {
        currentPlayer = Color.RED;
        lastX = lastY = -1;
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize+10;
        this.padY = stoneSize+10;
        this.cellWidth = (canvasWidth - 2*padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2*padX) / (rows - 1);
        this.boardWidth = (cols-1)*cellWidth;
        this.boardHeight = (rows-1)*cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        frame.setHelp("Red's Turn");
        frame.setWarn("");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // calculate the row and column of click
                int realX = (e.getX() - padX + cellWidth/2) / cellWidth;
                int realY = (e.getY() - padY + cellHeight/2) / cellHeight;

                // check if within bounds
                if(! (realX >=0 && realX < cols && realY >= 0 && realY < rows))
                    return;

                // check if point already exists
                for(Stone stone : stones) {
                    if(stone.x == realX && stone.y == realY)
                        return;
                }

                // check if there is a road between last spot and this one
                boolean foundRoad = false;
                if(lastX >= 0 && lastY >= 0)
                {
                    for(Road road : roads) {
                        if(road.x1 == lastX && road.y1 == lastY && road.x2 == realX && road.y2 == realY)
                            foundRoad = true;
                        else if(road.x1 == realX && road.y1 == realY && road.x2 == lastX && road.y2 == lastY)
                            foundRoad = true;
                    }
                    if (!foundRoad) return;
                }
                if(lastX<0 || foundRoad) {

                    // if everything is ok, add point
                    lastX = realX;
                    lastY = realY;
                    stones.add(new Stone(realX, realY, currentPlayer));
                    repaint();

                    // update which player's turn it is
                    if(currentPlayer == Color.RED) {
                        currentPlayer = Color.BLUE;
                        frame.setHelp("Blue's Turn");
                    }
                    else {
                        currentPlayer = Color.RED;
                        frame.setHelp("Red's Turn");
                    }

                    // move is done. has a player won?
                    int[] offx = {1, 0, -1, 0};
                    int[] offy = {0, 1, 0, -1};
                    boolean atLeastOneMoveLeft = false;
                    for(int i=0;i<4;i++) {
                        int pozx = lastX + offx[i];
                        int pozy = lastY + offy[i];
                        if(pozx<0 || pozy<0||pozx >= cols || pozy >= rows) continue;
                        boolean isEmpty = true, isReachable = false;
                        for(Stone stone: stones) {
                            if(stone.x == pozx && stone.y == pozy) {
                                isEmpty = false;
                                break;
                            }
                        }
                        isReachable = isRoadBetween(new Point(lastX, lastY), new Point(pozx, pozy));
                        if(isEmpty && isReachable) {
                            atLeastOneMoveLeft = true;
                            break;
                        }
                    }
                    if(!atLeastOneMoveLeft) {
                        for(Stone stone: stones) {
                            if(stone.x == lastX && stone.y == lastY)
                            {
                                if(stone.player == Color.BLUE)
                                    frame.setWarn("WINNER: BLUE");
                                else
                                    frame.setWarn("WINNER: RED");
                                break;
                            }
                        }
                    }
                }
            }
        });
        // generate pathways
        roads.clear();
        double chance = 0.6;
        for(int row = 0;row < rows;row++)
        {
            for(int col=0;col<cols-1;col++) {
                double x = Math.random() * 2;
                if(x > chance) {
                    roads.add(new Road(col, row, col+1, row));
                }
            }
        }
        for(int col = 0;col < cols;col++)
        {
            for(int row=0;row<rows-1;row++) {
                double x = Math.random() * 2;
                if(x > chance) {
                    roads.add(new Road(col, row, col, row+1));
                }
            }
        }
    }

    private void drawStone(Graphics2D g, int x, int y, Color color) {
        g.setColor(color); // Or any color you want for the stone
        g2.setColor(color); // Or any color you want for the stone

        g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
        g2.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);

    }

    public void deleteStones() {
        stones.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.paintComponent(g2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2.setColor(Color.WHITE);
        g2d.fillRect(0, 0, canvasWidth, canvasHeight);
        g2.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g2d);
        paintGrid(g2);
        g2d.setColor(Color.BLACK);
        g2.setColor(Color.BLACK);
        if(lastX >= 0 && lastY >= 0)
            g2d.fillOval(padX + lastX * cellWidth-(int)(stoneSize*0.85), padY + lastY * cellHeight-(int)(stoneSize*0.85), (int) (1.7*stoneSize), (int) (1.7*stoneSize));
            g2.fillOval(padX + lastX * cellWidth-(int)(stoneSize*0.85), padY + lastY * cellHeight-(int)(stoneSize*0.85), (int) (1.7*stoneSize), (int) (1.7*stoneSize));

        for (Stone stone : stones) {
            drawStone(g2d, padX + stone.x * cellWidth, padY + stone.y * cellHeight, stone.player);
            drawStone(g2, padX + stone.x * cellWidth, padY + stone.y * cellHeight, stone.player);
        }
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g2.setColor(Color.DARK_GRAY);

        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
            g2.drawLine(x1, y1, x2, y2);

        }
        //vertical lines
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
            g2.drawLine(x1, y1, x2, y2);

        }
        // stone slots
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g2.setColor(Color.LIGHT_GRAY);

                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                g2.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);

            }
        }


        g.setColor(Color.BLACK);
        g2.setColor(Color.BLACK);

        g.setStroke(new BasicStroke(5));
        g2.setStroke(new BasicStroke(5));

        for(Road road : roads) {
            int realX1 = padX + road.x1 * cellWidth;
            int realX2 = padX + road.x2 * cellWidth;
            int realY1 = padY + road.y1 * cellHeight;
            int realY2 = padY + road.y2 * cellHeight;

            g.drawLine(realX1, realY1, realX2, realY2);
            g2.drawLine(realX1, realY1, realX2, realY2);
        }
        g.setStroke(new BasicStroke(1));
        g2.setStroke(new BasicStroke(1));
    }

    public void exportPNG() {
        File outputFile = new File("./gameState.png");
        try {
            ImageIO.write(bufferedImage, "PNG", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
