/*
 * Copyright (C)
 *     2015-2016 Pavel Kunyavskiy
 *     2016-2016 Eugene Kurpilyansky
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package net.pavelk.tlschema.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import net.pavelk.tlschema.TLSchemaFileType;

public class TLSchemaElementFactory {
    public static TLSchemaLcIdentNs createLcIdentNs(Project project, String name) {
        final TLSchemaFile file = createFile(project, String.format("%s = A;", name));
        return ((TLSchemaConstructorDeclarations) file.getFirstChild())
                .getDeclarationList()
                .get(0)
                .getCombinator();
    }

    public static TLSchemaUcIdentNs createUcIdentNs(Project project, String name) {
        final TLSchemaFile file = createFile(project, String.format("a = %s;", name));
        return ((TLSchemaConstructorDeclarations) file.getFirstChild())
                .getDeclarationList()
                .get(0)
                .getResultType()
                .getBoxedTypeIdent()
                .getUcIdentNs();
    }

    public static TLSchemaVarIdent createVarIdent(Project project, String name) {
        final TLSchemaFile file = createFile(project, String.format("a %s:int = A;", name));
        return ((TLSchemaConstructorDeclarations) file.getFirstChild())
                .getDeclarationList()
                .get(0)
                .getCombinatorDecl()
                .getArgList()
                .get(0)
                .getArgNamed()
                .getVarIdentOpt()
                .getVarIdent();
    }

    private static TLSchemaFile createFile(Project project, String text) {
        String name = "dummy.tlschema";
        return (TLSchemaFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, TLSchemaFileType.INSTANCE, text);
    }
}