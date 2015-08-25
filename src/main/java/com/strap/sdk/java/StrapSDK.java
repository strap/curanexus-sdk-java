package com.strap.sdk.java;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.strap.sdk.java.models.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcellebonterre
 */
public class StrapSDK extends StrapSDKBase {

    public StrapSDK(String token) throws StrapResponseParseException  {
        super(token);
    }


    protected Map<String, String> resetCurrentPage(Map<String, String> params) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        return params;
    }
    
    public ReportList activity(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        PagedResponse res = (PagedResponse) super.call("activity", "GET", params);
        List<ReportModel> rs = jsonToReportList(res.getData());
        ReportList rv = new ReportList(this, "activity", params, res);
        return rv;
    }

    public Report report(Map<String, String> params) throws StrapMalformedUrlException, StrapResourceNotFoundException, UnsupportedEncodingException  {
        if (!params.containsKey("id")) {
            throw new StrapMalformedUrlException("No ID provided.");
        }
        PagedResponse res = (PagedResponse) super.call("report", "GET", params);
        Report rv = new Report(this,"report",params,res);
        return rv;

    }

    public ReportList today() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        Map<String, String> params = new HashMap<>();
        return this.today(params);
    }

    public ReportList today(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        params = resetCurrentPage(params);
        PagedResponse res = (PagedResponse) super.call("today", "GET", params);
        ReportList rv = new ReportList(this, "today", params, res);
        return rv;
    }

    public ReportList week() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        Map<String, String> params = new HashMap<>();
        return this.week(params);
    }

    public ReportList week(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        params = resetCurrentPage(params);
        PagedResponse res = (PagedResponse) super.call("week", "GET", params);
        ReportList rv = new ReportList(this, "week", params, res);
        return rv;
    }

    public ReportList month() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        Map<String, String> params = new HashMap<>();
        return this.month(params);
    }

    public ReportList month(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        params = resetCurrentPage(params);
        PagedResponse res = (PagedResponse) super.call("month", "GET", params);
        ReportList rv = new ReportList(this, "month", params, res);
        return rv;
    }

    public UserList users() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        Map<String, String> params = new HashMap<>();
        return this.users(params);
    }

    public UserList users(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        params = resetCurrentPage(params);
        PagedResponse res = (PagedResponse) super.call("users", "GET", params);
        UserList rv = new UserList(this, "month", params, res);
        return rv;
    }

    public Trigger trigger(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException  {
        PagedResponse res = (PagedResponse) super.call("trigger", "GET", params);
        Trigger rv = new Trigger(this, "trigger", params, res);
        return rv;
    }

    public JobModel[] jobs() throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException, StrapResponseParseException {
        return this.jobs(new HashMap<String, String>());
    }

    public JobModel[] jobs(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("job", "GET", params)).getData();

        return (JobModel[]) jsonToModel(res, JobModel[].class);

    }

    public SegmentModel jobData(Map<String, String> params) throws StrapResourceNotFoundException, UnsupportedEncodingException, StrapMalformedUrlException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("job_data", "GET", params)).getData();

        return (SegmentModel) jsonToModel(res, SegmentModel.class);

    }

    public JobModel createJob(JobRequestModel data) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = (String) super.call("job", "POST", new HashMap<String, String>(), data);

        return (JobModel) jsonToModel(res, JobModel.class);

    }

    public JobModel updateJob(Map<String, String> params, JobRequestModel data) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = (String) super.call("job", "PUT", params, data);

        return (JobModel) jsonToModel(res, JobModel.class);

    }

    public void deleteJob(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException {
        super.call("job", "DELETE", params);
    }


    public BehaviorModel behavior(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("behavior", "GET", params)).getData();


        return (BehaviorModel) jsonToModel(res, BehaviorModel.class);

    }


    public SegmentModel segmentation() throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        return segmentation(new HashMap<String, String>());

    }

    public SegmentModel segmentation(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("segmentation", "GET", params)).getData();

        return (SegmentModel) jsonToModel(res, SegmentModel.class);


    }

    public ReportDetailsModel[] reportDetails(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("raw", "GET", params)).getData();

        return (ReportDetailsModel[]) jsonToModel(res, ReportDetailsModel[].class);

    }

    public ReportWorkoutModel[] workout(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("report_workout", "GET", params)).getData();

        return (ReportWorkoutModel[]) jsonToModel(res, ReportWorkoutModel[].class);

    }

    public ReportFoodModel[] food(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("report_food", "GET", params)).getData();

        return (ReportFoodModel[]) jsonToModel(res, ReportFoodModel[].class);

    }

    public LinkedTreeMap<String, Object> trend(Map<String, String> params) throws StrapMalformedUrlException, UnsupportedEncodingException, StrapResourceNotFoundException, StrapResponseParseException {

        String res = ((PagedResponse) super.call("trend", "GET", params)).getData();

        return (LinkedTreeMap<String, Object>) jsonToModel(res, LinkedTreeMap.class);

    }


    protected ArrayList<ReportModel> jsonToReportList(String jsonStr) {
        Type reportType = new TypeToken<ArrayList<ReportModel>>() { }.getType();
        ArrayList<ReportModel> rv = super.JSON.fromJson(jsonStr, reportType);
        return rv;
    }

    private Object jsonToModel(String res, Class type) throws StrapResponseParseException {

        Type resType = TypeToken.get(type).getType();

        try {
            return type.cast(JSON.fromJson(res, resType));
        } catch(JsonSyntaxException ex) {
            throw new StrapResponseParseException("Response not valid: Check your request parameters.");
        }
    }

}
