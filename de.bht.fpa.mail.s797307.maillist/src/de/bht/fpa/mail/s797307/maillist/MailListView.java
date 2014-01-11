package de.bht.fpa.mail.s797307.maillist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class MailListView extends ViewPart {
  public MailListView() {
  }

  private static final int IMPORTANCE_PERCENT_WIDTH = 70;
  private static final int RECEIVED_PERCENT_WIDTH = 140;
  private static final int READ_PERCENT_WIDTH = 40;
  private static final int SENDER_PERCENT_WIDTH = 160;
  private static final int RECIPIENTS_PERCENT_WIDTH = 160;
  private static final int SUBJECT_PERCENT_WIDTH = 250;
  private final Collection<Message> messages = new ArrayList<Message>();
  private TableViewer tableViewer;
  private Text searchText;

  @Override
  public void createPartControl(Composite parent) {

    parent.setLayout(new GridLayout(2, false));

    Label lblNewLabel = new Label(parent, SWT.NONE);
    lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
    lblNewLabel.setText("Search Messages");

    searchText = new Text(parent, SWT.BORDER);
    searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

    Label label = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
    label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
    Composite tableArea = new Composite(parent, SWT.NONE);
    tableArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

    TableViewerBuilder t = new TableViewerBuilder(tableArea);
    ColumnBuilder importance = t.createColumn("Importance");
    importance.setPixelWidth(IMPORTANCE_PERCENT_WIDTH);
    importance.bindToProperty("importance");
    importance.build();
    ColumnBuilder received = t.createColumn("Received");
    received.setPixelWidth(RECEIVED_PERCENT_WIDTH);
    received.useAsDefaultSortColumn();
    StringValueFormatter dateFormat = Formatter.forDate(SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM));
    received.format(dateFormat);
    received.bindToProperty("received");
    received.build();
    ColumnBuilder read = t.createColumn("Read");
    read.setPixelWidth(READ_PERCENT_WIDTH);
    read.bindToValue(new BaseValue<Message>() {
      @Override
      public Object get(Message message) {
        return message.isRead();
      }
    });
    read.build();
    ColumnBuilder sender = t.createColumn("Sender");
    sender.setPixelWidth(SENDER_PERCENT_WIDTH);
    sender.bindToProperty("sender.email");
    sender.build();
    ColumnBuilder recipients = t.createColumn("Recipients");
    recipients.setPixelWidth(RECIPIENTS_PERCENT_WIDTH);
    recipients.bindToValue(new BaseValue<Message>() {
      @Override
      public Object get(Message message) {
        StringBuilder sb = new StringBuilder();
        String prefix = ", ";
        for (Recipient recipient : message.getRecipients()) {
          sb.append(", " + recipient.getEmail());
        }
        if (sb.length() <= prefix.length()) {
          return "";
        }
        return sb.substring(prefix.length()).toString();
      }
    });
    recipients.build();
    ColumnBuilder subject = t.createColumn("Subject");
    subject.setPixelWidth(SUBJECT_PERCENT_WIDTH);
    subject.bindToProperty("subject");
    subject.build();
    t.setInput(messages);
    tableViewer = t.getTableViewer();
    getSite().setSelectionProvider(tableViewer);
    getViewSite().getPage().addSelectionListener(new MaillistListener(this));

    final StringBuilder currentSearch = new StringBuilder();
    tableViewer.addFilter(new ViewerFilter() {
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof Message) {
          // • Message.subject,
          // • Message.text,
          // • Message.received,
          // • Message.sent,
          // • Message.receiver.email, Message.receiver.personal,
          // • Message.sender.email Message.sender.personal

          Message message = (Message) element;
          String searchString = currentSearch.toString();
          StringBuilder receiverContent = new StringBuilder();
          for (Recipient recipient : message.getRecipients()) {
            receiverContent.append(recipient.getEmail());
            receiverContent.append(recipient.getPersonal());

          }
          boolean textFound = message.getSubject().contains(searchString) || message.getText().contains(searchString)
              || message.getReceived().toString().contains(searchString)
              || message.getSent().toString().contains(searchString)
              || message.getSender().getEmail().contains(searchString)
              || message.getSender().getPersonal().contains(searchString)
              || receiverContent.toString().contains(searchString);
          return textFound;
        }
        return false;
      }
    });

    searchText.addKeyListener(new KeyListener() {
      @Override
      public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
        currentSearch.delete(0, currentSearch.length());
        currentSearch.append(searchText.getText());
        tableViewer.refresh();
      }

      @Override
      public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
      }
    });

  }

  public void addMessage(Message message) {
    messages.add(message);
  }

  public void updateMessages() {
    if (messages.size() > 0) {
      tableViewer.setInput(messages);
    }
  }

  public void refresh() {
    tableViewer.refresh();
  }

  public void clear() {
    tableViewer.remove(messages);
    messages.clear();
  }

  @Override
  public void setFocus() {

  }

}
