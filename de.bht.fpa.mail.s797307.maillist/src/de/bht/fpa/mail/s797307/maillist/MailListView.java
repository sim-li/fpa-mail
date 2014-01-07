package de.bht.fpa.mail.s797307.maillist;

import java.util.Collection;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.ralfebert.rcputils.properties.BaseValue;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.ICellFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MailListView extends ViewPart {
    private static final int IMPORTANCE_PERCENT_WIDTH = 70;
    private static final int RECEIVED_PERCENT_WIDTH = 140;
    private static final int READ_PERCENT_WIDTH = 40;
    private static final int SENDER_PERCENT_WIDTH = 160;
    private static final int RECIPIENTS_PERCENT_WIDTH = 160;
    private static final int SUBJECT_PERCENT_WIDTH = 250;
    private TableViewer tableViewer;

    @Override
    public void createPartControl(Composite parent) {
        TableViewerBuilder t = new TableViewerBuilder(parent);
        ColumnBuilder importance = t.createColumn("Importance");
        importance.setPixelWidth(IMPORTANCE_PERCENT_WIDTH);
        importance.bindToProperty("importance");
        importance.build();
        ColumnBuilder received = t.createColumn("Received");
        received.setPixelWidth(RECEIVED_PERCENT_WIDTH);
        received.bindToProperty("received");
        received.build();
        ColumnBuilder read = t.createColumn("Read");
        read.setPixelWidth(READ_PERCENT_WIDTH);
        // read.bindToProperty("read");
        read.format(new ICellFormatter() {

            @Override
            public void formatCell(ViewerCell cell, Object value) {
            }
        });
        read.build();
        ColumnBuilder sender = t.createColumn("Sender");
        sender.setPixelWidth(SENDER_PERCENT_WIDTH);
        sender.bindToProperty("sender.email");
        sender.build();
        ColumnBuilder recipients = t.createColumn("Recipients");
        recipients.setPixelWidth(RECIPIENTS_PERCENT_WIDTH);
        // [[Message: id=1533104654051427387
        // sender=[Sender: id=2691333030509271361 email=schmidt_lola@home.de
        // personal=lola schmidt ]
        // received=Fri Aug 09 23:09:03 CEST 3918
        // subject=My pants are on the run
        // read=false
        // importance=high attachment=()
        // recipient=([Recipient: id=5579141307399660638 type=null
        // email=schmidt_frank@myspace.com personal=trude stulle ])
        recipients.bindToValue(new BaseValue<Message>() {
            @Override
            public Object get(Message message) {
                StringBuilder sb = new StringBuilder();
                String prefix = ", ";
                for (Recipient recipient : message.getRecipients()) {
                    sb.append(", " + recipient.getEmail());
                }

                return sb.substring(prefix.length()).toString();
            }
        });
        recipients.build();
        ColumnBuilder subject = t.createColumn("Subject");
        subject.setPixelWidth(SUBJECT_PERCENT_WIDTH);
        subject.bindToProperty("subject");
        subject.build();
        Collection<Message> messages = new RandomTestDataProvider(50).getMessages();
        System.out.println(messages);
        t.setInput(messages);
        tableViewer = t.getTableViewer();
    }

    @Override
    public void setFocus() {

    }

}
