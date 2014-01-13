package de.bht.fpa.mail.s797307.maillist;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.Viewer;

public class ExecutionController implements IExecutionListener {
  private final Viewer viewer;

  public ExecutionController(Viewer viewer) {
    this.viewer = viewer;
  }

  @Override
  public void notHandled(String commandId, NotHandledException exception) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postExecuteFailure(String commandId, ExecutionException exception) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postExecuteSuccess(String commandId, Object returnValue) {
    System.out.println("HELLO JAVA");
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
    // TODO Auto-generated method stub

  }

}
