/* 
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.japo.java.layers.managers.M1User;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class MEM extends MouseAdapter {

    // Referencia al GUI
    private final M1User gui;

    // Constructor
    public MEM(M1User gui) {
        this.gui = gui;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gui.procesarRaton(e);
    }
}
