/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.beans.Beans;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.gwt.common.util.AsyncTaskHandler;
import com.dawg6.gwt.common.util.DefaultCallback;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class SavePanel extends Composite {
	private final FileUpload fileUpload;
	private final Hidden clientKey;
	private final TextArea textArea;
	private final TextBox nameField;
	private final FlexTable browserPanel;
	private final FlexTable filePanel;
	private final FlexTable textPanel;
	private final TabPanel tabPanel;
	private FormListener listener;
	private final FormPanel loadPanel;
	private final ListBox storageList;

	public SavePanel() {

		CaptionPanel captionPanel = new CaptionPanel("Save/Load");
		initWidget(captionPanel);

		FlexTable flexTable_3 = new FlexTable();
		flexTable_3.setCellPadding(2);
		captionPanel.setContentWidget(flexTable_3);

		tabPanel = new TabPanel();
		flexTable_3.setWidget(0, 0, tabPanel);

		browserPanel = new FlexTable();
		browserPanel.setCellPadding(2);

		if (Storage.isLocalStorageSupported()) {
			tabPanel.add(browserPanel, "Browser Storage", false);
		}

		storageList = new ListBox();
		browserPanel.setWidget(0, 0, storageList);
		storageList.setWidth("100%");
		storageList.setVisibleItemCount(5);

		nameField = new TextBox();
		nameField.setVisibleLength(30);
		nameField.setText("Enter a Name");
		browserPanel.setWidget(1, 0, nameField);

		Button btnNewButton = new Button("New button");
		browserPanel.setWidget(1, 1, btnNewButton);
		btnNewButton.setText("Add");

		btnNewButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addLocalStorage();
			}
		});

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		horizontalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		browserPanel.setWidget(2, 0, horizontalPanel);

		Button button_6 = new Button("New button");
		button_6.setText("Save");
		horizontalPanel.add(button_6);

		button_6.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveLocalStorage();
			}
		});

		Button button_5 = new Button("New button");
		horizontalPanel.add(button_5);
		button_5.setText("Restore");

		button_5.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				restoreLocalStorage();
			}
		});

		Button button_2 = new Button("New button");
		horizontalPanel.add(button_2);
		button_2.setText("Delete");

		button_2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteLocalStorage();
			}
		});

		Button btnNewButton_1 = new Button("New button");
		horizontalPanel.add(btnNewButton_1);
		btnNewButton_1.setText("Rename");

		btnNewButton_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				renameLocalStorage();
			}
		});

		browserPanel.getFlexCellFormatter().setColSpan(0, 0, 1);
		browserPanel.getFlexCellFormatter().setColSpan(2, 0, 1);
		browserPanel.getFlexCellFormatter().setColSpan(0, 0, 2);
		browserPanel.getFlexCellFormatter().setColSpan(2, 0, 2);
		browserPanel.getCellFormatter().setHorizontalAlignment(2, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		filePanel = new FlexTable();
		filePanel.setCellPadding(2);
		tabPanel.add(filePanel, "Local File", false);

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		filePanel.setWidget(0, 0, horizontalPanel_1);

		loadPanel = new FormPanel();
		loadPanel.setMethod(FormPanel.METHOD_POST);
		loadPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		loadPanel.setAction("loadData");
		horizontalPanel_1.add(loadPanel);

		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		loadPanel.setWidget(horizontalPanel_2);
		horizontalPanel_2.setSize("100%", "100%");

		fileUpload = new FileUpload();
		fileUpload.setName("file");
		horizontalPanel_2.add(fileUpload);

		clientKey = new Hidden("client");
		horizontalPanel_2.add(clientKey);
		filePanel.getFlexCellFormatter().setColSpan(0, 0, 1);

		Button button = new Button("Save Data...");
		filePanel.setWidget(1, 0, button);
		button.setText("Save File");

		Button button_1 = new Button("Load Data...");
		filePanel.setWidget(1, 1, button_1);
		button_1.setText("Load File");
		filePanel.getFlexCellFormatter().setColSpan(0, 0, 2);
		filePanel.getCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		filePanel.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		button_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadFile();

			}
		});

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveFile();

			}
		});

		textPanel = new FlexTable();
		textPanel.setCellPadding(2);
		tabPanel.add(textPanel, "Copy/Paste", false);

		textArea = new TextArea();
		textArea.setText("Paste previously saved form data here and click \"Restore.\" Or press \"Current\" to paste current Form data.");
		textArea.setVisibleLines(5);
		textPanel.setWidget(0, 0, textArea);
		textArea.setSize("100%", "");

		Button button_3 = new Button("Save Data...");
		button_3.setText("Current");
		textPanel.setWidget(1, 0, button_3);

		textArea.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				textArea.selectAll();
			}
		});

		nameField.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nameField.selectAll();
			}
		});

		button_3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (listener != null) {

					FormData data = listener.getFormData();

					JSONObject obj = JsonUtil.toJSONObject(data);
					textArea.setText(JsonUtil.formatJsonText(obj.toString()));
					textArea.selectAll();
				}

			}
		});

		Button button_4 = new Button("Load Data...");
		button_4.setText("Restore");

		button_4.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (listener != null) {
					String text = textArea.getText();

					FormData data = JsonUtil.parseFormData(text);
					
					listener.setFormData(data);
					textArea.setText("");
				}

			}
		});

		textPanel.setWidget(1, 1, button_4);
		textPanel.getFlexCellFormatter().setColSpan(0, 0, 2);
		textPanel.getCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		textPanel.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		flexTable_3.getFlexCellFormatter().setColSpan(0, 0, 1);

		storageList.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				int i = storageList.getSelectedIndex();

				if (i >= 0) {
					String name = storageList.getItemText(i);

					if (name != null)
						nameField.setText(name);
				}

			}
		});
	}

	protected void renameLocalStorage() {
		String name = getName();

		if (name != null) {
			final Storage s = getStorage();

			if (s != null) {
				final int i = storageList.getSelectedIndex();

				if (i < 0) {
					ApplicationPanel.showErrorDialog("Select a Storage entry");
				} else {
					String key = storageList.getValue(i);
					String value = s.getItem(key);
					storageList.removeItem(i);
					s.removeItem(key);
					key = STORAGE_KEY + name;
					s.setItem(key, value);
					storageList.insertItem(name, key, i);
				}
			}
		}
	}

	protected void deleteLocalStorage() {
		final Storage s = getStorage();

		if (s != null) {
			final int i = storageList.getSelectedIndex();

			if (i < 0) {
				ApplicationPanel.showErrorDialog("Select a Storage entry");
			} else {
				String key = storageList.getValue(i);
				storageList.removeItem(i);
				s.removeItem(key);
			}
		}
	}

	protected void restoreLocalStorage() {
		final Storage s = getStorage();

		if (s != null) {
			final int i = storageList.getSelectedIndex();

			if (i < 0) {
				ApplicationPanel.showErrorDialog("Select a Storage entry");
			} else {
				String key = storageList.getValue(i);
				String value = s.getItem(key);

				if (value == null)
					value = "";

				final String json = value;

				if (listener != null) {

					FormData data = JsonUtil.parseFormData(json);
					listener.setFormData(data);
					
				}
			}
		}
	}

	protected void saveLocalStorage() {
		final Storage s = getStorage();

		if (s != null) {
			final int i = storageList.getSelectedIndex();

			if (i < 0) {
				ApplicationPanel.showErrorDialog("Select a Storage entry");
			} else {
				if (listener != null) {
					final FormData data = listener.getFormData();

					JSONObject obj = JsonUtil.toJSONObject(data);
					String key = storageList.getValue(i);
					s.setItem(key, obj.toString());
					
					ApplicationPanel.showInfoDialog("Configuration Saved.");
				}
			}
		}
	}

	private static final String STORAGE_KEY = "FormData.";

	protected void addLocalStorage() {
		final String name = getName();

		if (name != null) {
			final Storage s = getStorage();

			if (s != null) {

				final String key = STORAGE_KEY + name;

				if (s.getItem(key) != null) {
					ApplicationPanel
							.showErrorDialog("That entry already exists.");
				} else {
					final FormData data = listener.getFormData();

					JSONObject obj = JsonUtil.toJSONObject(data);
					s.setItem(key, obj.toString());
					storageList.addItem(name, key);
					
					storageList.setSelectedIndex(storageList.getItemCount() - 1);
					
					ApplicationPanel.showInfoDialog("Configuration Saved");
				}
			}
		}
	}

	private Storage getStorage() {
		Storage s = Storage.getLocalStorageIfSupported();

		if (s == null) {
			ApplicationPanel
					.showErrorDialog("Local Storage not supported by your browser");
		}

		return s;
	}

	private String getName() {
		String name = nameField.getText();

		if ((name == null) || (name.trim().length() == 0)) {
			ApplicationPanel.showErrorDialog("Enter a name");
			return null;
		} else {
			return name;
		}
	}

	@Override
	public void onLoad() {
		tabPanel.selectTab(0);

		if (!Beans.isDesignTime()) {
			Storage s = Storage.getLocalStorageIfSupported();

			if (s != null) {

				for (int i = 0; i < s.getLength(); i++) {
					String key = s.key(i);

					if ((key != null) && key.startsWith(STORAGE_KEY)) {
						String name = key.substring(STORAGE_KEY.length());
						storageList.addItem(name, key);
					}
				}
			}
		}
	}

	protected void loadFile() {

		if (listener != null) {
			String file = fileUpload.getFilename();

			if ((file == null) || (file.trim().length() == 0)) {
				ApplicationPanel.showErrorDialog("Error",
						"Please select a file");
				return;
			}

			this.clientKey.setValue(String.valueOf(Math.random() + "."
					+ Math.random()));
			this.loadPanel.submit();

			AsyncTaskHandler dialog = ApplicationPanel.showWaitDialogBox(
					"Please Wait", "Loading...");

			int maxTries = 5;

			loadData(dialog, maxTries);
		}
	}

	private void loadData(final AsyncTaskHandler dialog, final int maxTries) {
		final String client = this.clientKey.getValue();

		Service.getInstance().getClientData(client,
				new DefaultCallback<FormData>() {

					@Override
					protected void doOnSuccess(FormData result) {

						if (result == null) {
							if (maxTries > 0)
								loadData(dialog, maxTries - 1);
							else {
								ApplicationPanel.showErrorDialog("Error",
										"Unable to load file");
								dialog.taskCompleted();
							}
						} else {
							listener.setFormData(result);
							dialog.taskCompleted();
						}
					}
				});

	}

	protected void saveFile() {

		if (listener != null) {
			final FormData data = listener.getFormData();

			JSONObject obj = JsonUtil.toJSONObject(data);
			MainPanel.saveFormData("dh-dps-calc.json",
					JsonUtil.formatJsonText(obj.toString()), "false");
			
		}
	}

	public void setFormListener(FormListener listener) {
		this.listener = listener;
	}

	public interface FormListener {
		FormData getFormData();

		void setFormData(FormData data);
	}
	
}
