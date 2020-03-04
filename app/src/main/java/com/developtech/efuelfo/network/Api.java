package com.developtech.efuelfo.network;

import com.developtech.efuelfo.model.DispenseTransRequestModel;
import com.developtech.efuelfo.model.ResultModel;
import com.developtech.efuelfo.model.requestModel.AddExpenseRequestModel;
import com.developtech.efuelfo.model.requestModel.AddFavFuelRequestModel;
import com.developtech.efuelfo.model.requestModel.AddFuelStationRequestModel;
import com.developtech.efuelfo.model.requestModel.AddRefuelRequestModel;
import com.developtech.efuelfo.model.requestModel.AddServiceRequestModel;
import com.developtech.efuelfo.model.requestModel.AgreementRequestModel;
import com.developtech.efuelfo.model.requestModel.ChangePasswordRequestModel;
import com.developtech.efuelfo.model.requestModel.CreateScheduleRequestModel;
import com.developtech.efuelfo.model.requestModel.CreateTankRequestModel;
import com.developtech.efuelfo.model.requestModel.CreditRequestModel;
import com.developtech.efuelfo.model.requestModel.DeleteDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.EmailInvoiceRequestModel;
import com.developtech.efuelfo.model.requestModel.FindFuelStationManualRequestModel;
import com.developtech.efuelfo.model.requestModel.FindOperatorRequestModel;
import com.developtech.efuelfo.model.requestModel.FuelStationAvailabilityRequestModel;
import com.developtech.efuelfo.model.requestModel.GetCarModelsListRequest;
import com.developtech.efuelfo.model.requestModel.LocationRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorBlockRequestModel;
import com.developtech.efuelfo.model.requestModel.OperatorsResponseModel;
import com.developtech.efuelfo.model.requestModel.PaginationModel;
import com.developtech.efuelfo.model.requestModel.ParkTransRequestModel;
import com.developtech.efuelfo.model.requestModel.RefuelRequestDriverModel;
import com.developtech.efuelfo.model.requestModel.RegisterRequest;
import com.developtech.efuelfo.model.requestModel.ReportRequestModel;
import com.developtech.efuelfo.model.requestModel.ResendOtpRequestModel;
import com.developtech.efuelfo.model.requestModel.ResetPasswordRequestModel;
import com.developtech.efuelfo.model.requestModel.SearchScheduleRequestModel;
import com.developtech.efuelfo.model.requestModel.SettingsRequestModel;
import com.developtech.efuelfo.model.requestModel.SignInRequestModel;
import com.developtech.efuelfo.model.requestModel.SignUpRequestModel;
import com.developtech.efuelfo.model.requestModel.SignupRequestModelmobile;
import com.developtech.efuelfo.model.requestModel.SwitchCreditAgreementReqModel;
import com.developtech.efuelfo.model.requestModel.TankTypeUpdateRequest;
import com.developtech.efuelfo.model.requestModel.TerminateAgreementRequestModel;
import com.developtech.efuelfo.model.requestModel.TotalRequest;
import com.developtech.efuelfo.model.requestModel.TransDetailRequestModel;
import com.developtech.efuelfo.model.requestModel.TransRecievedRequestModel;
import com.developtech.efuelfo.model.requestModel.UpdateDriverRequestModel;
import com.developtech.efuelfo.model.requestModel.UpdateOperatorRequestModel;
import com.developtech.efuelfo.model.requestModel.UserForgetPassRequestModel;
import com.developtech.efuelfo.model.requestModel.VehicleByNumberRequestModel;
import com.developtech.efuelfo.model.requestModel.VehicleCompanyListRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyForgetPassRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyOtpRequestModel;
import com.developtech.efuelfo.model.requestModel.VerifyTransactionRequestModel;
import com.developtech.efuelfo.model.responseModel.AddFuelRequestResponseModel;
import com.developtech.efuelfo.model.responseModel.AddFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.AllVehicleResponseModel;
import com.developtech.efuelfo.model.responseModel.CompanyListResponseModel;
import com.developtech.efuelfo.model.responseModel.CountriesResponseModel;
import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.DriverLocationResponseModel;
import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;
import com.developtech.efuelfo.model.responseModel.ExtraNotificationModel;
import com.developtech.efuelfo.model.responseModel.FuelCompanyRespnseModel;
import com.developtech.efuelfo.model.responseModel.FuelStationByQRResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.FuelTypeResponseModel;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.developtech.efuelfo.model.responseModel.GetExpensesResponseModel;
import com.developtech.efuelfo.model.responseModel.GetFavoriteStationResponseModel;
import com.developtech.efuelfo.model.responseModel.GetFuelStationResponseModel;
import com.developtech.efuelfo.model.responseModel.GetServiceResponseModel;
import com.developtech.efuelfo.model.responseModel.GetVehicleCategoryResponse;
import com.developtech.efuelfo.model.responseModel.LanguageResponseModel;
import com.developtech.efuelfo.model.responseModel.NotificationResponseModel;
import com.developtech.efuelfo.model.responseModel.OnlineTransactionsResponseModel;
import com.developtech.efuelfo.model.responseModel.ParkTransactionResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;
import com.developtech.efuelfo.model.responseModel.PlacesApiModels.ParentAutoComplete;
import com.developtech.efuelfo.model.responseModel.RefuelingResponseModel;
import com.developtech.efuelfo.model.responseModel.ResetPasswordResponseModel;
import com.developtech.efuelfo.model.responseModel.SaleFuelTypeResponseModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.model.responseModel.SettingsResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModelmobile;
import com.developtech.efuelfo.model.responseModel.TankResponseModel;
import com.developtech.efuelfo.model.responseModel.TotalResponse;
import com.developtech.efuelfo.model.responseModel.VehicleByNumberResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyForgetPassResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyVehicleModel;
import com.developtech.efuelfo.model.responseModel.ViewCashTransactionResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by android on 9/5/17.
 */

public interface Api {

    @POST("auth/UserSignUp")
    Observable<ResultModel<SignUpResponseModel>> signUp(@Body SignUpRequestModel model);
    @POST("auth/UserSignUp")
    Observable<ResultModel<SignUpResponseModelmobile>> signUp1(@Body SignupRequestModelmobile model);

    @POST("auth/userSignIn")
    Observable<ResultModel<SignInResponseModel>> signIn(@Body SignInRequestModel model);

    @POST("auth/verifyOtp")
    Observable<ResultModel<SignInResponseModel>> verifyOtp(@Body VerifyOtpRequestModel model);

    @POST("auth/userForgotPassword")
    Observable<ResultModel<SignUpResponseModel>> userForgotPassword(@Body UserForgetPassRequestModel model);

    @POST("auth/verifyForgotPassword")
    Observable<ResultModel<VerifyForgetPassResponseModel>> verifyForgotPassword(@Body VerifyForgetPassRequestModel model);

    @POST("auth/resetPassword")
    Observable<ResultModel<ResetPasswordResponseModel>> resetPassword(@Body ResetPasswordRequestModel model);

    @POST("changePassword")
    Observable<ResultModel> changePassword(@Body ChangePasswordRequestModel model);

    @POST("FuelStation/create")
    Observable<ResultModel<AddFuelStationResponseModel>> addFuelStation(@Body AddFuelStationRequestModel requestModel);

    @GET("FuelStation/find")
    Observable<ResultModel<List<GetFuelStationResponseModel>>> getFuelStations();

    @Multipart
    @POST("profile/updateProfile")
    Observable<ResultModel<SignInResponseModel>> updateProfile(@Part("firstName") RequestBody firstName, @Part("lastName") RequestBody lastName,
                                                               @Part("email") RequestBody email, @Part("mobileNumber")
                                                                       RequestBody mobileNumber, @Part("address") RequestBody address, @Part("country")
                                                                       RequestBody country, @Part("countryCode") RequestBody countryCode,
                                                               @Part("language") RequestBody language, @Part("pincode") RequestBody pincode,
                                                               @Part("altMobileNumber") RequestBody alternateMobile, @Part MultipartBody.Part image);


    @Multipart
    @POST("profile/updateProfile")
    Observable<ResultModel<SignInResponseModel>> updateProfile(@Part("firstName") RequestBody firstName,
                                                               @Part("email") RequestBody email, @Part("mobileNumber")
                                                                       RequestBody mobileNumber, @Part("address") RequestBody address, @Part("country")
                                                                       RequestBody country, @Part("countryCode") RequestBody countryCode,
                                                               @Part("language") RequestBody language, @Part("pincode") RequestBody pincode,
                                                               @Part("altMobileNumber") RequestBody alternateMobile, @Part MultipartBody.Part image);
    /////////////////   Vco   ///////////////

    @Multipart
    @POST("Vehicle/create")
    Observable<ResultModel<AllVehicleResponseModel>> addVehicle(@Part("company") RequestBody company, @Part("model") RequestBody model, @Part("color") RequestBody color,
                                                                @Part("alias") RequestBody alias, @Part("vehicleNumber") RequestBody vehicleNumber,
                                                                @Part("fuelType") RequestBody fuelType, @Part("fuelCapacity") RequestBody fuelCapacity,
                                                                @Part("driver") RequestBody driverId, @Part("vehicleCategory") RequestBody vehicleCategory,
                                                                @Part("vehicleType") RequestBody vehicleType, @Part("selfDriven") RequestBody selfDriven,
                                                                @Part MultipartBody.Part image);

    @Multipart
    @POST("Driver/create")
    Observable<ResultModel<GetDriverResponseModel>> addDriver(@Part("firstName") RequestBody firstName, @Part("lastName") RequestBody lastName, @Part("mobileNumber") RequestBody mobileNumber,
                                                              @Part("email") RequestBody email, @Part("gender") RequestBody gender, @Part("vehicle") RequestBody vehicle,
                                                              @Part("altMobileNumber") RequestBody altMobile, @Part("countryCode") RequestBody countryCode, @Part MultipartBody.Part image);

    @GET("Driver/find")
    Observable<ResultModel<List<GetDriverResponseModel>>> getAllDrivers();

    @Multipart
    @POST("Operator/create")
    Observable<ResultModel<AddFuelStationResponseModel>> addOperator(@Part("firstName") RequestBody fname, @Part("lastName") RequestBody lname, @Part("email") RequestBody email,
                                                                     @Part("mobileNumber") RequestBody mobileNumber, @Part("countryCode") RequestBody countryCode,
                                                                     @Part("fuelStationId") RequestBody fuelStationId, @Part("isManager") RequestBody isManager, @Part MultipartBody.Part image);

    @POST("RefuelRequest")
    Observable<ResultModel<AddFuelRequestResponseModel>> addRefuelRequest(@Body AddRefuelRequestModel requestModel);

    @POST("Vehicle/find")
    Observable<ResultModel<List<AllVehicleResponseModel>>> getVehicleList();

    @POST("FuelType")
    Observable<ResultModel<List<FuelTypeResponseModel>>> getFuelType();

    @POST("RefuelRequest/pendingTransactions")
    Observable<ResultModel<List<PaymentHistoryResponseModel>>> vcoPendingTransaction();

    @POST("RefuelRequest/completeTransactions")
    Observable<ResultModel<List<PaymentHistoryResponseModel>>> vcoCompleteTransaction(@Body SearchScheduleRequestModel requestModel);

    @POST("AgreementRequest/pendingAgreement")
    Observable<ResultModel<List<CreditAgreementResponseModel>>> vcoPendingAgreement(@Body SearchScheduleRequestModel requestModel);

    @POST("AgreementRequest/completeAgreement")
    Observable<ResultModel<List<CreditAgreementResponseModel>>> vcoAgreementHistory(@Body SearchScheduleRequestModel requestModel);

    @POST("AgreementRequest/terminate")
    Observable<ResultModel<AddFuelStationResponseModel>> terminateAgreement(@Body TerminateAgreementRequestModel requestModel);

    @POST("VehicleOwner/getFavourites")
    Observable<ResultModel<GetFavoriteStationResponseModel>> getFavFuel(@Body LocationRequestModel model);

    @POST("VehicleOwner/favourites")
    Observable<ResultModel<AddFuelStationResponseModel>> addFavFuel(@Body AddFavFuelRequestModel requestModel);

    @Multipart
    @POST("Driver/update")
    Observable<ResultModel<AddFuelStationResponseModel>> updateDriver(@Part("firstName") RequestBody firstName, @Part("lastName") RequestBody lastName, @Part("mobileNumber") RequestBody mobileNumber,
                                                                      @Part("email") RequestBody email, @Part("gender") RequestBody gender, @Part("vehicle") RequestBody vehicle, @Part("driverId") RequestBody driverId, @Part MultipartBody.Part image);

    @POST("Driver/delete")
    Observable<ResultModel<AddFuelStationResponseModel>> deleteDriver(@Body DeleteDriverRequestModel requestModel);

    @Multipart
    @POST("Vehicle/update")
    Observable<ResultModel<AllVehicleResponseModel>> updateVehicle(@Part("requestId") RequestBody requestId, @Part("company") RequestBody company, @Part("model") RequestBody model, @Part("color") RequestBody color,
                                                                   @Part("alias") RequestBody alias, @Part("vehicleNumber") RequestBody vehicleNumber,
                                                                   @Part("fuelType") RequestBody fuelType, @Part("fuelCapacity") RequestBody fuelCapacity,
                                                                   @Part("driver") RequestBody driverId, @Part("vehicleCategory") RequestBody vehicleCategory,
                                                                   @Part("vehicleType") RequestBody vehicleType, @Part("selfDriven") RequestBody selfDriven,
                                                                   @Part MultipartBody.Part image);

    @POST("Vehicle/delete")
    Observable<ResultModel<AddFuelStationResponseModel>> deleteVehicle(@Body DeleteDriverRequestModel requestModel);

    @POST("FuelStation/nearByFuelStation")
    Observable<ResultModel<List<FuelStationResponseModel>>> getNearByFuelStation(@Body LocationRequestModel requestModel);

    @POST("AgreementRequest/create")
    Observable<ResultModel<AddFuelStationResponseModel>> requestCredit(@Body CreditRequestModel requestModel);

    @POST("VehicleCompany/companyList")
    Observable<ResultModel<List<CompanyListResponseModel>>> getCompanyList(@Body VehicleCompanyListRequestModel requestModel);

    @POST("VehicleModel/modelsList")
    Observable<ResultModel<List<CompanyListResponseModel>>> getModelsList(@Body GetCarModelsListRequest request);

    @GET("Country")
    Observable<ResultModel<List<CountriesResponseModel>>> getCountiesList();

    @POST("VehicleOwner/trackDriverLocation")
    Observable<ResultModel<DriverLocationResponseModel>> getDriverLocation(@Body UpdateDriverRequestModel requestModel);

    @GET("Language")
    Observable<ResultModel<List<LanguageResponseModel>>> getLanguages();

    @POST("FuelStation/findViaQR")
    Observable<ResultModel<FuelStationByQRResponseModel>> findStationByQr(@Body DeleteDriverRequestModel requestModel);

    @POST("StationOwner/sendInvoice")
    Observable<ResultModel<PaymentHistoryResponseModel>> sendInvoice(@Body EmailInvoiceRequestModel requestModel);

    @POST("StationOwner/blockUnblockFuelStation")
    Observable<ResultModel<OperatorsResponseModel>> blockUnblockOPRFuelStation(@Body OperatorBlockRequestModel requestModel);

    @POST("RefuelRequest/completeTransactions")
    Observable<ResultModel<List<PaymentHistoryResponseModel>>> getDriverTransaction();

    @POST("auth/socialSignIn")
    Observable<ResultModel<SignInResponseModel>> socialSignIn(@Body SignInRequestModel requestModel);

    @POST("VehicleOwner/trackDriver")
    Observable<ResultModel<AddFuelStationResponseModel>> trackDriver(@Body LocationRequestModel requestModel);

    @POST("ExpenseType/find")
    Observable<ResultModel<List<ExpenseTypeModel>>> getAvailableExpenseType();

    @POST("ServiceType/find")
    Observable<ResultModel<List<ExpenseTypeModel>>> getAvailableServiceType();

    @POST("ExpenseType/create")
    Observable<ResultModel<ExpenseTypeModel>> createExpenseType(@Body ExpenseTypeModel expenseTypeModel);

    @POST("ExpenseType/update")
    Observable<ResultModel<ExpenseTypeModel>> updateExpenseType(@Body ExpenseTypeModel expenseTypeModel);

    @POST("ExpenseType/delete")
    Observable<ResultModel<ExpenseTypeModel>> deleteExpenseType(@Body ExpenseTypeModel expenseTypeModel);

    @POST("Expense/create")
    Observable<ResultModel<ExpenseTypeModel>> createExpense(@Body AddExpenseRequestModel requestModel);

    @POST("Expense/find")
    Observable<ResultModel<List<GetExpensesResponseModel>>> getExpenses();

    @POST("Expense/update")
    Observable<ResultModel<ExpenseTypeModel>> updateExpense(@Body AddExpenseRequestModel requestModel);

    @POST("Expense/delete")
    Observable<ResultModel<ExpenseTypeModel>> deleteExpense(@Body AddExpenseRequestModel requestModel);

    @POST("ServiceType/create")
    Observable<ResultModel<ExpenseTypeModel>> createServiceType(@Body ExpenseTypeModel expenseTypeModel);

    @POST("Service/create")
    Observable<ResultModel<ExpenseTypeModel>> createService(@Body AddServiceRequestModel requestModel);

    @POST("Service/find")
    Observable<ResultModel<List<GetServiceResponseModel>>> getServices();

    @POST("Service/update")
    Observable<ResultModel<AddServiceRequestModel>> updateService(@Body AddServiceRequestModel requestModel);

    @POST("Service/delete")
    Observable<ResultModel<ExpenseTypeModel>> deleteService(@Body AddExpenseRequestModel requestModel);

    @POST("auth/resendOtp")
    Observable<ResultModel<ResendOtpRequestModel>> resendOtp(@Body ResendOtpRequestModel model);

    @POST("Operator/find")
    Observable<ResultModel<List<OperatorsResponseModel>>> getOperators(@Body FindOperatorRequestModel requestModel);

    @POST("Operator/delete")
    Observable<ResultModel<DeleteDriverRequestModel>> deleteOperator(@Body DeleteDriverRequestModel requestModel);

    @GET("VehicleCategory")
    Observable<ResultModel<List<GetVehicleCategoryResponse>>> getVehicleCategories();

    @POST("FuelStation/findManually")
    Observable<ResultModel<FuelStationResponseModel>> findManualStation(@Body FindFuelStationManualRequestModel requestModel);

    @GET("Driver/driverList")
    Observable<ResultModel<List<GetDriverResponseModel>>> getDrivers();

    @POST("Vehicle/vehicleList")
    Observable<ResultModel<List<AllVehicleResponseModel>>> getVehicles();

    @GET("FuelCompany")
    Observable<ResultModel<List<FuelCompanyRespnseModel>>> getFuelCompanies();

    @POST("FuelStation/update")
    Observable<ResultModel<AddFuelStationResponseModel>> updateFuelStation(@Body AddFuelStationRequestModel requestModel);

    @POST("StationOwner/sendQrCode")
    Observable<ResultModel<AddFuelStationResponseModel>> sendQr(@Body AddFuelStationRequestModel requestModel);

    @POST("Schedules/create")
    Observable<ResultModel<AddFuelRequestResponseModel>> createSchedule(@Body CreateScheduleRequestModel requestModel);

    @POST("Schedules/find")
    Observable<ResultModel<List<SchedulesResponseModel>>> findSchedules(@Body AddFuelStationRequestModel requestModel);

    @POST("Schedules/update")
    Observable<ResultModel<AddFuelRequestResponseModel>> updateSchedule(@Body CreateScheduleRequestModel requestModel);

    @POST("Schedules/oldSchedules")
    Observable<ResultModel<List<SchedulesResponseModel>>> oldSchedules(@Body AddFuelStationRequestModel requestModel);

    @POST("Schedules/searchSchedules")
    Observable<ResultModel<List<SchedulesResponseModel>>> searchSchedule(@Body SearchScheduleRequestModel requestModel);

    @POST("Vehicle/findViaNumber")
    Observable<ResultModel<VehicleByNumberResponseModel>> vehicleByNumber(@Body VehicleByNumberRequestModel requestModel);

    @POST("RefuelRequest/viewParkTransaction")
    Observable<ResultModel<List<ParkTransactionResponseModel>>> findParkTransactions(@Body ParkTransRequestModel requestModel);

    @POST("Tanks/create")
    Observable<ResultModel<CreateTankRequestModel>> createTank(@Body CreateTankRequestModel requestModel);

    @POST("Tanks/find")
    Observable<ResultModel<List<TankResponseModel>>> findTank(@Body SearchScheduleRequestModel requestModel);

    @POST("FuelStation/isAvailable")
    Observable<ResultModel<FuelStationAvailabilityRequestModel>> setStationAvailability(@Body FuelStationAvailabilityRequestModel requestModel);

    @POST("Tanks/update")
    Observable<ResultModel<CreateTankRequestModel>> updateTank(@Body CreateTankRequestModel requestModel);

    @POST("Transaction/saleByFuelType")
    Observable<ResultModel<List<SaleFuelTypeResponseModel>>> findSaleByFuelType(@Body SearchScheduleRequestModel requestModel);

    @POST("RefuelRequest/parkTransaction")
    Observable<ResultModel> requestParkTransaction(@Body AddRefuelRequestModel refuelRequestModel);

    @POST("CashTransaction/create")
    Observable<ResultModel> createCashTransaction(@Body AddRefuelRequestModel refuelRequestModel);

    @POST("RefuelRequest/verifyTransaction")
    Observable<ResultModel<List<VerifyVehicleModel>>> verifyTransaction(@Body VerifyTransactionRequestModel requestModel);

    @POST("CashTransaction/find")
    Observable<ResultModel<List<ViewCashTransactionResponseModel>>> findCashTransaction(@Body PaginationModel paginationModel);

    @POST("CashTransaction/find")
    Observable<ResultModel<List<ViewCashTransactionResponseModel>>> findCashTransaction(@Body SearchScheduleRequestModel searchScheduleRequestModel);

    @POST("Transaction/find")
    Observable<ResultModel<List<OnlineTransactionsResponseModel>>> findOnlineTransaction(@Body SearchScheduleRequestModel requestModel);

    @POST("RefuelRequest/dispenseVerifyTransaction")
    Observable<ResultModel> dispenseVerifyTrans(@Body DispenseTransRequestModel requestModel);

    @POST("Device/register")
    Observable<ResultModel> registerDevice(@Body RegisterRequest requestModel);

    @POST("RefuelRequest/FsoNotificationTrasactionDetails")
    Observable<ResultModel<PaymentHistoryResponseModel>> getTransDetail(@Body TransDetailRequestModel requestModel);

    @POST("RefuelRequest/acceptingRefuelRequest")
    Observable<ResultModel> refuelRequestDriver(@Body RefuelRequestDriverModel requestModel);

    @POST("Tanks/delete")
    Observable<ResultModel> deleteTank(@Body ExtraNotificationModel requestModel);

    @POST("AgreementRequest/acceptedAgreementRequest")
    Observable<ResultModel> agreementRequest(@Body AgreementRequestModel requestModel);

    @POST("RefuelRequest/deleteTransaction")
    Observable<ResultModel> deletePendingRefuel(@Body DeleteDriverRequestModel requestModel);

    @POST("AgreementRequest/agreemetDetail")
    Observable<ResultModel<CreditAgreementResponseModel>> agreementDetail(@Body AgreementRequestModel requestModel);

    @POST("Device/logout")
    Observable<ResultModel> logout(@Body RegisterRequest registerRequest);

    @POST("public/getSettings")
    Observable<ResultModel<SettingsResponseModel>> getSettings(@Body SettingsRequestModel requestModel);

    @POST("Operator/findViaNumber")
    Observable<ResultModel<OperatorsResponseModel>> findOperatoryByNumber(@Body FindOperatorRequestModel requestModel);

    @POST("RefuelRequest/verifyTransactionViaQrCode")
    Observable<ResultModel<List<VerifyVehicleModel>>> verifyTransByQrCode(@Body DeleteDriverRequestModel requestModel);

    @POST("Notification/find")
    Observable<ResultModel<List<NotificationResponseModel>>> getNotifications(@Body PaginationModel requestModel);

    @POST("Transaction/recieved")
    Observable<ResultModel> transRecieved(@Body TransRecievedRequestModel requestModel);

    @POST("VerifiedUser")
    Observable<ResultModel> verifyPassword(@Body ChangePasswordRequestModel requestModel);

    @POST("RefuelRequest/getAmount")
    Observable<ResultModel<TotalResponse>> getTotal(@Body TotalRequest totalRequest);

    @POST("Operator/update")
    Observable<ResultModel> updateOperator(@Body UpdateOperatorRequestModel requestModel);

    @GET("place/autocomplete/json?key=AIzaSyCz_EVx5QVmJFdKQ7516wkMH3yZ4er2eKo")
    Call<ParentAutoComplete> getPlaces(@Query("input") String input);


    @POST("RefuelRequest/fuelStationRefuelReport")
    Observable<ResultModel<RefuelingResponseModel>> refuelReport(@Body ReportRequestModel requestModel);

    @POST("Tanks/updateTank")
    Observable<ResultModel> updateTankType(@Body TankTypeUpdateRequest requestModel);

    @GET("Operator/fuelStationList")
    Observable<ResultModel<List<GetFuelStationResponseModel>>> getFuelStationOwners();

    @POST("FuelStation/isCreditAgreement")
    Observable<ResultModel> switchCreditAgreement(@Body SwitchCreditAgreementReqModel reqModel);
}
