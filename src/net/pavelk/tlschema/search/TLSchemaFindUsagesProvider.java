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

package net.pavelk.tlschema.search;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import net.pavelk.tlschema.TLSchemaLexerAdapter;
import net.pavelk.tlschema.psi.TLSchemaLcIdentNs;
import net.pavelk.tlschema.psi.TLSchemaTypes;
import net.pavelk.tlschema.psi.TLSchemaUcIdentNs;
import net.pavelk.tlschema.psi.TLSchemaVarIdent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class TLSchemaFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new TLSchemaWordsScanner(new TLSchemaLexerAdapter(),
                TokenSet.create(TLSchemaTypes.UC_IDENT, TLSchemaTypes.LC_IDENT, TLSchemaTypes.DOT));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement element) {
        return element instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement element) {
        return null;
//        return HelpID.FIND_OTHER_USAGES;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof TLSchemaVarIdent) {
            return "variable";
        } else if (element instanceof TLSchemaLcIdentNs) {
            return "combinator";
        } else if (element instanceof TLSchemaUcIdentNs) {
            return "type";
        } else {
            return "";
        }
//        return ((PsiNamedElement)element).getName();
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof PsiNamedElement) {
            return ((PsiNamedElement) element).getName() + "";
        } else {
            return "";
        }
//        if (element instanceof SimpleProperty) {
//            return ((SimpleProperty) element).getKey();
//        } else {
//            return "";
//        }
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof PsiNamedElement) {
            return ((PsiNamedElement) element).getName() + "";
        } else {
            return "";
        }
//        if (element instanceof TLSchemaDeclaration) {
//            return ((SimpleProperty) element).getKey() + ":" + ((SimpleProperty) element).getValue();
//        } else {
//            return "";
//        }
    }
}
