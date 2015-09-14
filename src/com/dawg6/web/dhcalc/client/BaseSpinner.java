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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ValueBox;

public abstract class BaseSpinner<T> extends Composite {

	protected final ValueBox<T> box;
	protected final Button up;
	protected final Button down;
	protected T min;
	protected T max;

	/**
	 * @wbp.parser.constructor
	 */
	protected BaseSpinner(ValueBox<T> box, String text, T min, T max) {
		this.box = box;
		this.min = min;
		this.max = max;

		box.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				keyPressed();
			}});

		box.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				normalize();
			}});
		
		box.addBlurHandler(new BlurHandler(){

			@Override
			public void onBlur(BlurEvent event) {
				normalize();
			}});
		
		box.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				normalize();
			}
		});
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("spinner");
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		initWidget(horizontalPanel);
		
		down = new Button();
		down.setText("-");
		down.setStyleName("spinnerDownButton");
		horizontalPanel.add(down);
		
		down.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				increment(-1);
			}});
		
		box.setStyleName("spinnerBox");
		box.setText(text);
		horizontalPanel.add(box);
		
		box.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				BaseSpinner.this.box.selectAll();
			}});
		
		up = new Button();
		up.setText("+");
		up.setStyleName("spinnerUpButton");
		horizontalPanel.add(up);
		
		up.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				increment(1);
			}});
	}
	
	protected abstract void keyPressed();

	protected void normalize() {
		setValue(getValue());
	}

	protected abstract void increment(int step);

	protected abstract T bound(T value);
	
	protected abstract T parse(String text);
	
	public void setValue(T value) {
		T a = getValue();
		T b = bound(value);
		
		if (!isEqual(a, b)) {
			this.box.setValue(b);
			DomEvent.fireNativeEvent(Document.get().createChangeEvent(), box);
		}
	}
	
	public T getValue() {
		T value =  this.box.getValue();
		
		if (value == null) {
			value = min;
			this.box.setValue(value);
		}
		
		return value;
	}
	
	public void setVisibleLength(int w) {
		box.setVisibleLength(w);
	}
	
	public String getText() {
		return box.getText();
	}
	
	public void setText(String text) {
		setValue(parse(text));
	}
	
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
		normalize();
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
		normalize();
	}

	@Override
	public void setTitle(String title) {
		box.setTitle(title);
	}
	
	@Override
	public String getTitle() {
		return box.getTitle();
	}
	
	protected abstract boolean isEqual(T a, T b);
}
