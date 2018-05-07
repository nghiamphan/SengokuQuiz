package com.nphan.android.sengokuquiz;

public class Question {
    private int mTextId;
    private int mOptionAId;
    private int mOptionBId;
    private int mOptionCId;
    private int mOptionDId;
    private int mAnswerId; // 0:A, 1:B, 2:C, 3:D

    public Question(int textId, int optionAId, int optionBId, int optionCId, int optionDId, int answerId) {
        mTextId = textId;
        mOptionAId = optionAId;
        mOptionBId = optionBId;
        mOptionCId = optionCId;
        mOptionDId = optionDId;
        mAnswerId = answerId;
    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int textId) {
        mTextId = textId;
    }

    public int getOptionAId() {
        return mOptionAId;
    }

    public void setOptionAId(int optionAId) {
        mOptionAId = optionAId;
    }

    public int getOptionBId() {
        return mOptionBId;
    }

    public void setOptionBId(int optionBId) {
        mOptionBId = optionBId;
    }

    public int getOptionCId() {
        return mOptionCId;
    }

    public void setOptionCId(int optionCId) {
        mOptionCId = optionCId;
    }

    public int getOptionDId() {
        return mOptionDId;
    }

    public void setOptionDId(int optionDId) {
        mOptionDId = optionDId;
    }

    public int getAnswerId() {
        return mAnswerId;
    }

    public void setAnswerId(int answer) {
        mAnswerId = answer;
    }
}
