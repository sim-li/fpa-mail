/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s797307.util.filters;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class implements the {@link IFilter} interface, but does not filter
 * anything (Null-Object Pattern).
 */
public class NullFilter extends BasicFilter {

  @Override
  public boolean filter(Message message) {
    return true;
  }
}
