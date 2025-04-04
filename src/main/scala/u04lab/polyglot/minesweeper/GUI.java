package u04lab.polyglot.minesweeper;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Cell<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int numberOfBombs) {
        this.logics = new LogicsImpl2(size, numberOfBombs);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Cell<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = this.logics.hit(pos.x(), pos.y()); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                bt.setEnabled(false);
                drawBoard();            	
            }
            boolean isThereVictory = this.logics.hasWon(); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Cell<Integer,Integer> pos = buttons.get(bt);
                    logics.toggleFlag(pos.x(), pos.y());
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Cell<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            var position = entry.getValue();
            var button = entry.getKey();
            if (this.logics.hasBomb(position.x(), position.y())) {
                button.setText("*");
            }
            button.setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            var pos = entry.getValue();
            var button = entry.getKey();
            var adjacentBombs = this.logics.adjacentBombs(pos.x(), pos.y());
            button.setText(this.logics.hasFlag(pos.x(), pos.y()) ? "F" : " ");
            adjacentBombs.ifPresent(count -> {
                button.setEnabled(false);
                button.setText(String.valueOf(count));
            });
    	}
    }
    
}
