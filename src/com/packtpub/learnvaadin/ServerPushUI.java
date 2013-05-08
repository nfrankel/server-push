package com.packtpub.learnvaadin;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Push
public class ServerPushUI extends UI {

	private Label timeLabel = new Label();

	@Override
	protected void init(VaadinRequest request) {

		HorizontalLayout layout = new HorizontalLayout(timeLabel);

		layout.setMargin(true);

		setContent(layout);

		new Thread(new EndlessRefresherRunnable()).start();
	}

	private class EndlessRefresherRunnable implements Runnable {

		@Override
		public void run() {

			while (true) {

				try {

					Thread.sleep(1000);

				} catch (InterruptedException e) {}

				access(new LabelUpdaterRunnable(timeLabel));
			}
		}
	}
}