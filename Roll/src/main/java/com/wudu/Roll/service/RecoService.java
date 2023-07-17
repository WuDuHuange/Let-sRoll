package com.wudu.Roll.service;

import java.util.List;

public interface RecoService {
    public String roll(String username, int userid);

    public List listReco(String username, int userid);

    public String deleteReco(int userid);
}
