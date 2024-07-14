package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CaseNote {
    private String note;

    public CaseNote() {
        this.note = "";
    }
    public static CaseNote[] createDummyNotes(int count) {
        CaseNote[] dummyNotes = new CaseNote[count];

        for (int i = 0; i < count; i++) {
            CaseNote dummyNote = new CaseNote();
            dummyNote.setNote("Dummy note");
            dummyNotes[i] = dummyNote;
        }

        return dummyNotes;
    }

}