/*******************************************************************************
 * Copyright (c) 2013 United States Government as represented by the 
 * Administrator of the National Aeronautics and Space Administration. 
 * All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package gov.nasa.util.ui.jface.preference;

import gov.nasa.util.ui.LastPath;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

/**
 * @author mallan
 */
public class FileListFieldEditor extends ListEditor {
    private String separator  = ",";
    private String dialogTitle;

    /**
     * disabled
     */
    protected FileListFieldEditor() {
        //
    }

    /**
     * Creates a file list field editor.
     */
    public FileListFieldEditor(String name, String labelText, String dialogTitle, String separator, Composite parent) {
        init(name, labelText);
        this.dialogTitle = dialogTitle;
        this.separator = separator;
        createControl(parent);
    }

    @Override
    protected String createList(String[] items) {
        StringBuffer stringList = new StringBuffer("");
        for (String item : items) {
            stringList.append(item);
            stringList.append(separator);
        }
        return stringList.toString();
    }

    @Override
    protected String getNewInputObject() {
        String retVal = null;
        FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN | SWT.SINGLE);
        dialog.setText(dialogTitle);
        dialog.setFilterPath(LastPath.get(this));
        if (dialog.open() != null) {
            final String filename = dialog.getFileName();
            final String filePath = dialog.getFilterPath();
            LastPath.set(this, filePath);
            return filePath+File.separator+filename;
        }
        return retVal;
    }

    @Override
    protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, separator);
        ArrayList<String> strings = new ArrayList<String>();
        while (st.hasMoreElements()) {
            strings.add(st.nextToken());
        }
        return strings.toArray(new String[strings.size()]);
    }
}
