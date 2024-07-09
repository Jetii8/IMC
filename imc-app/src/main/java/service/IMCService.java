package com.example.services;

import com.example.models.IMCRecord;

import java.util.List;

public interface IMCService {
    void addIMCRecord(IMCRecord imcRecord);
    List<IMCRecord> getIMCRecordsByUserId(int userId);
}
