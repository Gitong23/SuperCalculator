import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Caculator extends JFrame{
    private JPanel MainPanel;
    private JTextField textField;
    private JButton PlusButton;
    private JButton button4;
    private JButton MinusButton;
    private JButton ClearButton;
    private JButton Multiplie;
    private JButton backButton;
    private JButton a7Button;
    private JButton a9Button;
    private JButton a8Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton DotButton;
    private JButton EqualButton;
    private JTextArea historyTextArea;
    private JLabel StatusLabel;

    private ArrayList<Character> TypingNum=new ArrayList<Character>(); //Collect Typing Number
    private ArrayList<String> Status =new ArrayList<String>();//Collect Numbers are Calculating
    private ArrayList<String> History=new ArrayList<String>();//When have final result The History of last calculation is collected in this Arraylist.
    private double result=0.0; //Collect Value Of Result
    private boolean HaveFinalResult =false;//To check status when have final result
    //HaveFinalResult=true When Press Button =
    public Caculator(String title)
    {
            super(title);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setContentPane(MainPanel);
            this.pack();
            History.add("History: ");
            a0Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('0');
                }
            });
            a1Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('1');
                }
            });
            a2Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('2');
                }
            });
            a3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('3');
                }
            });
            a4Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('4');
                }
            });
            a5Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('5');
                }
            });
            a6Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('6');
                }
            });
            a7Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('7');
                }
            });
            a8Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('8');
                }
            });
            a9Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingMethod('9');
                }
            });
            backButton.addActionListener(new ActionListener() {//delete last number in Arraylist
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (TypingNum.size() > 0) {
                        TypingNum.remove(TypingNum.size() - 1);
                        textField.setText(ArraylistToString(TypingNum));
                    }
                }
            });
            ClearButton.addActionListener(new ActionListener() {//Clear this calculation
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingNum.clear();
                    Status.clear();
                    StatusLabel.setText("");
                    textField.setText("");
                    result = 0;
                }
            });
            DotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TypingNum.add('.');
                    textField.setText(ArraylistToString(TypingNum));
                }
            });
            PlusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CollectResult("+");
                }
            });
            EqualButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FinalResult();
                }
            });

            MinusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CollectResult("-");
                }
            });
            Multiplie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CollectResult("x");
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CollectResult("/");
                }
            });
    }
////////////////////////////////METHOD//////////////////////////////////////////////
    public String ArraylistToString(ArrayList<Character> NumText) //Convert Arraylist of Char of String to String
    {
        StringBuilder builder = new StringBuilder(NumText.size());
        for(Character ch: NumText)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    public String StringListToString(ArrayList<String> StatusBar) //Convert Arraylist of String to String
    {
        String str="";
        for (int i=0;i<StatusBar.size();i++)
        {
            str=str+StatusBar.get(i);
        }
        return str;
    }
    public boolean ClearingLastCalculation(boolean HaveResult)//Reset Last calculation if = button is already press
    {
        if (HaveResult)
        {
            TypingNum.clear();
            Status.clear();
            StatusLabel.setText(StringListToString(Status));
        }
        return false;
    }
    public void CollectResult(String operator)
    {
        if (HaveFinalResult)
        {
            ReCalculate(operator);
        }
        else if (!HaveFinalResult)
        {
            if (Status.size()==0)
            {
                result=Double.parseDouble(ArraylistToString(TypingNum));
            }
            else if ((Status.get(Status.size()-1)).equals("+"))
            {
                result=result+Double.parseDouble(ArraylistToString(TypingNum));
            }
            else if ((Status.get(Status.size()-1)).equals("-"))
            {
                result=result-Double.parseDouble(ArraylistToString(TypingNum));
            }
            else if ((Status.get(Status.size()-1)).equals("x"))
            {
                result=result*Double.parseDouble(ArraylistToString(TypingNum));
            }
            else if ((Status.get(Status.size()-1)).equals("/"))
            {
                result=result/Double.parseDouble(ArraylistToString(TypingNum));
            }
            Status.add(ArraylistToString(TypingNum));
            Status.add(operator);
            StatusLabel.setText(StringListToString(Status));
            TypingNum.clear();
            textField.setText("");
        }
    }
    public void FinalResult() //Algorithm To calculate in the past number input before button= are pressed
    {
        if (Status.get(Status.size()-1).equals("+"))
        {
            result=result+Double.parseDouble(ArraylistToString(TypingNum));
        }
        else if (Status.get(Status.size()-1).equals("-"))
        {
            result=result-Double.parseDouble(ArraylistToString(TypingNum));
        }
        else if (Status.get(Status.size()-1).equals("x"))
        {
            result=result*Double.parseDouble(ArraylistToString(TypingNum));
        }
        else if (Status.get(Status.size()-1).equals("/"))
        {
            result=result/Double.parseDouble(ArraylistToString(TypingNum));
        }
        Status.add(ArraylistToString(TypingNum));
        Status.add("=");
        Status.add(String.valueOf(result));
        StatusLabel.setText(StringListToString(Status)); //add =,result value in Status
        textField.setText(String.valueOf(result));
        History.add("\n"+StringListToString(Status));//add last calculation into History
        historyTextArea.setText(StringListToString(History));
        HaveFinalResult =true; //already have value of final result
        result=0;//reset result value
    }
    public void ReCalculate(String operator)//When = Button are pressed before //Next Calculation Start At Last Final Result
    {
            result=Double.parseDouble(textField.getText());
            TypingNum.clear();
            Status.clear();
            Status.add(textField.getText());
            Status.add(operator);
            StatusLabel.setText(StringListToString(Status));
            this.HaveFinalResult=false;
     }
    public void TypingMethod (char Num)//Add Number to TypingNumArraylist
    {
        HaveFinalResult = ClearingLastCalculation(HaveFinalResult); //Clear Last calculation when have final result
        TypingNum.add(Num);
        textField.setText(ArraylistToString(TypingNum));
    }
}
