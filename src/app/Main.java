package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import control.*;
import model.Block;
import view.BlockDisplay;

public class Main extends JFrame{
    
    private Block block;
    private BlockDisplay blockDisplay;
    private BlockPresenter blockPresenter;
    private HashMap<String,Command> commands;

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        this.setTitle("Block Shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    
    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }
    
    private HashMap<String, Command> createCommands(){
        HashMap<String,Command> commands = new HashMap<>();
        commands.put("Up", new UpCommand(block));
        commands.put("Down", new DownCommand(block));
        commands.put("Left", new LeftCommand(block));
        commands.put("Right", new RightCommand(block));
        return commands;
    }
    
    private JMenuBar toolbar() {
        JMenuBar result = new JMenuBar();
        result.setLayout(new FlowLayout(FlowLayout.CENTER));
        result.add(button("Up"));
        result.add(button("Down"));
        result.add(button("Left"));
        result.add(button("Right"));
        return result;
    }
    
    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(command).execute();
            }
        });
        return button;
    }
}