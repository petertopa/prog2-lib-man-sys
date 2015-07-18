package lms.view.main;

import javax.swing.JFrame;

import lms.controller.FrameWindowListener;
import lms.model.facade.LMSFacade;
import lms.model.facade.LMSModel;
import lms.view.MainView;



public class LMSDriver
{
   public static void main(String[] args)
   {
      //created new view
      LMSModel model = new LMSFacade();
    
      //create and display a new view
      MainView  mainView = new MainView(model);//map the model to the view
      mainView.setVisible(true);
      mainView.addWindowListener(new FrameWindowListener());
      mainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
      mainView.setTitle("OUA CPT221 - SP1 2014 - Assingment 2");
      
      
   }   
}
