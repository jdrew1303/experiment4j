package com.crypticmission.exp;

import com.crypticmission.exp.Experiment.TrialType;
import static com.crypticmission.exp.Experiment.TrialType.CANDIDATE;
import static com.crypticmission.exp.Experiment.TrialType.CONTROL;
import com.crypticmission.exp.util.Assert;
import java.time.Instant;

/**
 * Created by dannwebster on 10/12/14.
 * {
 :experiment => "widget-permissions",
 :first      => :control,
 :timestamp  => <a-Time-instance>,

 :candidate => {
 :duration  => 2.5,
 :exception => nil,
 :value     => 42
 },

 :control => {
 :duration  => 25.0,
 :exception => nil,
 :value     => 24
 }
 }
 */
public class Result<T> {
    private final String name;
    private final TrialType first;
    private final Instant timestamp;
    private final TrialResult<T> candidateResult;
    private final TrialResult<T> controlResult;

    public Result(String name, TrialType first, Instant timestamp, TrialResult<T> candidateResult, TrialResult<T> controlResult) {
        Assert.hasText(name, "name must be non-empty");
        Assert.notNull(first, "first must be non-null");
        Assert.notNull(timestamp, "timestamp must be non-null");
        Assert.notNull(controlResult, "controlResult must be non-null");
        Assert.notNull(candidateResult, "candidateResult must be non-null");
        this.name = name;
        this.first = first;
        this.timestamp = timestamp;
        this.candidateResult = candidateResult;
        this.controlResult = controlResult;
    }

    public String getName() { return name; }
    public TrialType getFirst() { return first; }
    public boolean getWasControlFirst() {return CONTROL.equals(first); }
    public boolean getWasCandidateFirst() {return CANDIDATE.equals(first); }
    public Instant getTimestamp() { return timestamp; }

    public TrialResult getCandidateResult() { return candidateResult; }
    public TrialResult getControlResult() { return controlResult; }

}