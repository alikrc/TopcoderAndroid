package com.example.ali.topcoderandroid.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ali.topcoderandroid.Helpers.DateTimeParser;
import com.example.ali.topcoderandroid.Helpers.LogHelper;
import com.example.ali.topcoderandroid.Interfaces.ItemClickListener;
import com.example.ali.topcoderandroid.Models.ChallengeModel;
import com.example.ali.topcoderandroid.R;

import java.util.Arrays;

/**
 * Created by ali on 28.08.2015.
 */
public class ChallengeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final View mView;

    public final TextView tvChallengeName;
    public final TextView tvTechnologies;
    public final TextView tvChallengeType;
    public final TextView tvTotalPrize;
    public final TextView tvCurrentPhaseRemainingTime;
    public final TextView tvCurrentPhaseName;
    public final TextView tvRegistrationStartDate;
    public final TextView tvRegistrationEndDate;
    public final TextView tvSubmissionEndDate;

    ItemClickListener mItemClickListener;

    public ChallengeViewHolder(View view, ItemClickListener itemClickListener) {
        super(view);
        mView = view;
        this.tvChallengeName = (TextView) view.findViewById(R.id.tvChallengeName);
        this.tvTechnologies = (TextView) view.findViewById(R.id.tvTechnologies);
        this.tvChallengeType = (TextView) view.findViewById(R.id.tvChallengeType);
        this.tvTotalPrize = (TextView) view.findViewById(R.id.tvTotalPrize);
        this.tvCurrentPhaseRemainingTime = (TextView) view.findViewById(R.id.tvCurrentPhaseRemainingTime);
        this.tvCurrentPhaseName = (TextView) view.findViewById(R.id.tvCurrentPhaseName);
        this.tvRegistrationStartDate = (TextView) view.findViewById(R.id.tvRegistrationStartDate);
        this.tvRegistrationEndDate = (TextView) view.findViewById(R.id.tvRegistrationEndDate);
        this.tvSubmissionEndDate = (TextView) view.findViewById(R.id.tvSubmissionEndDate);

        mItemClickListener = itemClickListener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mItemClickListener.onItemClick(v, getAdapterPosition());
    }

    public void bindChallenge(ChallengeModel challenge) {

        String challengeName = String.valueOf(challenge.getChallengeName());

        String[] techNameArr = challenge.getTechnologies();
        String technologies = Arrays.toString(techNameArr).replace("[", "").replace("]", "");

        String challengeType = challenge.getChallengeType();
        String totalPrize = "$" + challenge.getTotalPrize();

        Long currentPhaseRemainingTime = challenge.getCurrentPhaseRemainingTime();
        String currentPhaseRemainingTimeString = DateTimeParser.ParseSecondToDayAndHour(challenge.getCurrentPhaseRemainingTime());
        String currentPhaseName = challenge.getCurrentPhaseName();

        String registrationStartDate = DateTimeParser.ParseDateTime(challenge.getRegistrationStartDate());
        String registrationEndDate = DateTimeParser.ParseDateTime(challenge.getRegistrationEndDate());
        String submissionEndDate = DateTimeParser.ParseDateTime(challenge.getSubmissionEndDate());

        tvTechnologies.setText(technologies);
        tvChallengeType.setText(challengeType);
        tvTotalPrize.setText(totalPrize);
        tvCurrentPhaseRemainingTime.setText(currentPhaseRemainingTimeString);
        if (currentPhaseRemainingTime < 7200) {
            try {
                int color = ContextCompat.getColor(mView.getContext(), R.color.redColor);

                tvCurrentPhaseRemainingTime.setTextColor(color); //todo error prone?
            } catch (Exception e) {
                LogHelper.Log(e);
            }
        }

        tvCurrentPhaseName.setText(currentPhaseName);
        tvRegistrationStartDate.setText(registrationStartDate);
        tvRegistrationEndDate.setText(registrationEndDate);
        tvSubmissionEndDate.setText(submissionEndDate);
        tvChallengeName.setText(challengeName);
    }


}