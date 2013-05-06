package com.packtpub.learnvaadin;

import static java.text.DateFormat.MEDIUM;
import static java.util.Locale.ENGLISH;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Push
public class ServerPushUI extends UI {

	private static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance(MEDIUM, ENGLISH);

	private Label timeLabel = new Label();

	@Override
	protected void init(VaadinRequest request) {

		timeLabel.setValue("Time: " + getCurrentTime());

		HorizontalLayout layout = new HorizontalLayout(timeLabel);
		layout.setMargin(true);

		setContent(layout);

		new InitializerThread().start();
	}

	protected String getCurrentTime() {

		Date date = Calendar.getInstance().getTime();

		return DATE_FORMAT.format(date);
	}

	private class InitializerThread extends Thread {

		@Override
		public void run() {

			while (true) {

				try {

					Thread.sleep(1000);

				} catch (InterruptedException e) {}

				access(new Runnable() {

					@Override
					public void run() {

						timeLabel.setValue("Time: " + getCurrentTime());
					}
				});
			}
		}
	}
}