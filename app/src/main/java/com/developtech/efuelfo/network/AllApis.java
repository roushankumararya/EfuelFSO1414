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
import com.developtech.efuelfo.model.responseModel.RefuelingResponseModel;
import com.developtech.efuelfo.model.responseModel.ResetPasswordResponseModel;
import com.developtech.efuelfo.model.responseModel.SaleFuelTypeResponseModel;
import com.developtech.efuelfo.model.responseModel.SchedulesResponseModel;
import com.developtech.efuelfo.model.responseModel.SettingsResponseModel;
import com.developtech.efuelfo.model.responseModel.SignInResponseModel;
import com.developtech.efuelfo.model.responseModel.SignUpResponseModel;
import com.developtech.efuelfo.model.responseModel.TankResponseModel;
import com.developtech.efuelfo.model.responseModel.TotalResponse;
import com.developtech.efuelfo.model.responseModel.VehicleByNumberResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyForgetPassResponseModel;
import com.developtech.efuelfo.model.responseModel.VerifyVehicleModel;
import com.developtech.efuelfo.model.responseModel.ViewCashTransactionResponseModel;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by developtech on 1/9/18.
 */

public class AllApis {

    Api api;

    @Inject
    AllApis(Api api) {
        this.api = api;
    }

    public Observable<ResultModel<OperatorsResponseModel>> blockUnblockOPRFuelStation(OperatorBlockRequestModel requestModel) {
        return api.blockUnblockOPRFuelStation(requestModel);
    }

    public Observable<ResultModel<SignUpResponseModel>> signUp(SignUpRequestModel requestModel) {
        return api.signUp(requestModel);
    }
    /*public Observable<ResultModel<SignUpResponseModelmobile>> signUp1(SignupRequestModelmobile signupRequestModelmobile) {
        return api.signUp1(signupRequestModelmobile);
    }*/
    public Observable<ResultModel<SignInResponseModel>> signIn(SignInRequestModel requestModel) {
        return api.signIn(requestModel);
    }

    public Observable<ResultModel<SignInResponseModel>> verifyOtp(VerifyOtpRequestModel requestModel) {
        return api.verifyOtp(requestModel);
    }

    public Observable<ResultModel<SignUpResponseModel>> userForgotPassword(UserForgetPassRequestModel requestModel) {
        return api.userForgotPassword(requestModel);
    }

    public Observable<ResultModel<VerifyForgetPassResponseModel>> verifyForgotPassword(VerifyForgetPassRequestModel requestModel) {
        return api.verifyForgotPassword(requestModel);
    }

    public Observable<ResultModel<ResetPasswordResponseModel>> resetPassword(ResetPasswordRequestModel requestModel) {
        return api.resetPassword(requestModel);
    }

    public Observable<ResultModel> changePassword(ChangePasswordRequestModel requestModel) {
        return api.changePassword(requestModel);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> addFuelStation(AddFuelStationRequestModel requestModel) {
        return api.addFuelStation(requestModel);
    }

    public Observable<ResultModel<SignInResponseModel>> updateProfile(RequestBody firstName, RequestBody lastName, RequestBody email
            , RequestBody mobileNumber, RequestBody address, RequestBody country, RequestBody countryCode, RequestBody language, RequestBody pincode, RequestBody alterMobile, MultipartBody.Part image) {
        return api.updateProfile(firstName, lastName, email, mobileNumber, address, country, countryCode, language, pincode, alterMobile, image);
    }

    public Observable<ResultModel<SignInResponseModel>> updateProfile(RequestBody firstName, RequestBody email
            , RequestBody mobileNumber, RequestBody address, RequestBody country, RequestBody countryCode, RequestBody language, RequestBody pincode, RequestBody alterMobile, MultipartBody.Part image) {
        return api.updateProfile(firstName, email, mobileNumber, address, country, countryCode, language, pincode, alterMobile, image);
    }

    public Observable<ResultModel<GetDriverResponseModel>> addDriver(RequestBody firstName, RequestBody lastName,
                                                                     RequestBody mobileNumber, RequestBody email, RequestBody gender,
                                                                     RequestBody vehicle, RequestBody altMobile, RequestBody countryCode, MultipartBody.Part image) {
        return api.addDriver(firstName, lastName, mobileNumber, email, gender, vehicle, altMobile, countryCode, image);
    }

    public Observable<ResultModel<List<GetDriverResponseModel>>> getAllDriver() {
        return api.getAllDrivers();
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> addOperator(RequestBody fname, RequestBody lname, RequestBody email, RequestBody mobileNumber, RequestBody countryCode, RequestBody fuelStationId, RequestBody isManager, MultipartBody.Part image) {
        return api.addOperator(fname, lname, email, mobileNumber, countryCode, fuelStationId, isManager, image);
    }

    public Observable<ResultModel<AddFuelRequestResponseModel>> addRefuelRequest(AddRefuelRequestModel requestModel) {
        return api.addRefuelRequest(requestModel);
    }

    public Observable<ResultModel<List<AllVehicleResponseModel>>> getVehicleList() {
        return api.getVehicleList();
    }

    public Observable<ResultModel<List<FuelTypeResponseModel>>> getFuelType() {
        return api.getFuelType();
    }

    public Observable<ResultModel<List<PaymentHistoryResponseModel>>> vcoPendingTransaction() {
        return api.vcoPendingTransaction();
    }

    public Observable<ResultModel<List<PaymentHistoryResponseModel>>> vcoCompleteTransaction(SearchScheduleRequestModel requestModel) {
        return api.vcoCompleteTransaction(requestModel);
    }


    public Observable<ResultModel<List<CreditAgreementResponseModel>>> vcoPendingAgreement(SearchScheduleRequestModel requestModel) {
        return api.vcoPendingAgreement(requestModel);
    }

    public Observable<ResultModel<List<CreditAgreementResponseModel>>> vcoAgreementHistory(SearchScheduleRequestModel requestModel) {
        return api.vcoAgreementHistory(requestModel);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> terminateAgreement(TerminateAgreementRequestModel model) {
        return api.terminateAgreement(model);
    }

    public Observable<ResultModel<GetFavoriteStationResponseModel>> getFavFuel(LocationRequestModel model) {
        return api.getFavFuel(model);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> addFavFuel(AddFavFuelRequestModel requestModel) {
        return api.addFavFuel(requestModel);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> updateDriver(RequestBody firstName, RequestBody lastName,
                                                                             RequestBody mobileNumber, RequestBody email, RequestBody gender, RequestBody vehicle, RequestBody driverId, MultipartBody.Part image) {
        return api.updateDriver(firstName, lastName, mobileNumber, email, gender, vehicle, driverId, image);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> deleteDriver(DeleteDriverRequestModel requestModel) {
        return api.deleteDriver(requestModel);

    }

    public Observable<ResultModel<AllVehicleResponseModel>> updateVehicle(RequestBody requestId, RequestBody company, RequestBody model, RequestBody color,
                                                                          RequestBody alias, RequestBody vehicleNumber, RequestBody fuelType,
                                                                          RequestBody fuelCapacity, RequestBody driverId, RequestBody vehicleCategory,
                                                                          RequestBody vehicleType, RequestBody selfDriven, MultipartBody.Part image) {
        return api.updateVehicle(requestId, company, model, color, alias, vehicleNumber, fuelType, fuelCapacity, driverId, vehicleCategory, vehicleType, selfDriven, image);
    }


    public Observable<ResultModel<AllVehicleResponseModel>> addVehicle(RequestBody company, RequestBody model, RequestBody color,
                                                                       RequestBody alias, RequestBody vehicleNumber, RequestBody fuelType,
                                                                       RequestBody fuelCapacity, RequestBody driverId, RequestBody vehicleCategory,
                                                                       RequestBody vehicleType, RequestBody selfDriven, MultipartBody.Part image) {
        return api.addVehicle(company, model, color, alias, vehicleNumber, fuelType, fuelCapacity, driverId, vehicleCategory, vehicleType, selfDriven, image);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> deleteVehicle(DeleteDriverRequestModel requestModel) {
        return api.deleteVehicle(requestModel);
    }

    public Observable<ResultModel<List<FuelStationResponseModel>>> getNearByFuelStation(LocationRequestModel requestModel) {
        return api.getNearByFuelStation(requestModel);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> requestCredit(CreditRequestModel requestModel) {
        return api.requestCredit(requestModel);
    }

    public Observable<ResultModel<List<CompanyListResponseModel>>> getCompanyList(VehicleCompanyListRequestModel requestModel) {
        return api.getCompanyList(requestModel);
    }

    public Observable<ResultModel<List<CompanyListResponseModel>>> getModelsList(GetCarModelsListRequest request) {
        return api.getModelsList(request);
    }

    public Observable<ResultModel<List<CountriesResponseModel>>> getCountriesList() {
        return api.getCountiesList();
    }

    public Observable<ResultModel<DriverLocationResponseModel>> getDriverLocation(UpdateDriverRequestModel requestModel) {
        return api.getDriverLocation(requestModel);
    }

    public Observable<ResultModel<List<LanguageResponseModel>>> getLanguages() {
        return api.getLanguages();
    }

    public Observable<ResultModel<FuelStationByQRResponseModel>> findStationByQr(DeleteDriverRequestModel requestModel) {
        return api.findStationByQr(requestModel);
    }

    public Observable<ResultModel<PaymentHistoryResponseModel>> sendInvoice(EmailInvoiceRequestModel requestModel) {
        return api.sendInvoice(requestModel);
    }

    public Observable<ResultModel<List<PaymentHistoryResponseModel>>> getDriverTransaction() {
        return api.getDriverTransaction();
    }

    public Observable<ResultModel<SignInResponseModel>> socialSignIn(SignInRequestModel requestModel) {
        return api.socialSignIn(requestModel);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> trackDriver(LocationRequestModel requestModel) {
        return api.trackDriver(requestModel);
    }

    public Observable<ResultModel<List<ExpenseTypeModel>>> getAvailableExpenseType() {
        return api.getAvailableExpenseType();
    }

    public Observable<ResultModel<List<ExpenseTypeModel>>> getAvailableServiceType() {
        return api.getAvailableServiceType();
    }

    public Observable<ResultModel<ExpenseTypeModel>> createExpenseType(ExpenseTypeModel expenseTypeModel) {
        return api.createExpenseType(expenseTypeModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> updateExpenseType(ExpenseTypeModel expenseTypeModel) {
        return api.updateExpenseType(expenseTypeModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> deleteExpenseType(ExpenseTypeModel expenseTypeModel) {
        return api.deleteExpenseType(expenseTypeModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> createExpense(AddExpenseRequestModel requestModel) {
        return api.createExpense(requestModel);
    }

    public Observable<ResultModel<List<GetExpensesResponseModel>>> getExpenses() {
        return api.getExpenses();
    }

    public Observable<ResultModel<ExpenseTypeModel>> updateExpense(AddExpenseRequestModel requestModel) {
        return api.updateExpense(requestModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> deleteExpense(AddExpenseRequestModel requestModel) {
        return api.deleteExpense(requestModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> createService(AddServiceRequestModel requestModel) {
        return api.createService(requestModel);
    }

    public Observable<ResultModel<List<GetServiceResponseModel>>> getServices() {
        return api.getServices();
    }

    public Observable<ResultModel<ExpenseTypeModel>> createServiceType(ExpenseTypeModel expenseTypeModel) {
        return api.createServiceType(expenseTypeModel);
    }

    public Observable<ResultModel<AddServiceRequestModel>> updateService(AddServiceRequestModel requestModel) {
        return api.updateService(requestModel);
    }

    public Observable<ResultModel<ExpenseTypeModel>> deleteService(AddExpenseRequestModel requestModel) {
        return api.deleteService(requestModel);
    }

    public Observable<ResultModel<ResendOtpRequestModel>> resendOtp(ResendOtpRequestModel model) {
        return api.resendOtp(model);
    }

    public Observable<ResultModel<List<OperatorsResponseModel>>> getOperators(FindOperatorRequestModel requestModel) {
        return api.getOperators(requestModel);
    }

    public Observable<ResultModel<DeleteDriverRequestModel>> deleteOperator(DeleteDriverRequestModel requestModel) {
        return api.deleteOperator(requestModel);
    }

    public Observable<ResultModel<List<GetFuelStationResponseModel>>> getFuelStations() {
        return api.getFuelStations();
    }

    public Observable<ResultModel<List<GetVehicleCategoryResponse>>> getVehicleCategories() {
        return api.getVehicleCategories();
    }

    public Observable<ResultModel<FuelStationResponseModel>> findManualStation(FindFuelStationManualRequestModel requestModel) {
        return api.findManualStation(requestModel);
    }

    public Observable<ResultModel<List<GetDriverResponseModel>>> getDrivers() {
        return api.getDrivers();
    }

    public Observable<ResultModel<List<AllVehicleResponseModel>>> getVehicles() {
        return api.getVehicles();
    }

    public Observable<ResultModel<List<FuelCompanyRespnseModel>>> getFuelCompanies() {
        return api.getFuelCompanies();
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> updateFuelStation(AddFuelStationRequestModel updateRequest) {
        return api.updateFuelStation(updateRequest);
    }

    public Observable<ResultModel<AddFuelStationResponseModel>> sendQr(AddFuelStationRequestModel updateRequest) {
        return api.sendQr(updateRequest);
    }

    public Observable<ResultModel<AddFuelRequestResponseModel>> createSchedule(CreateScheduleRequestModel requestModel) {
        return api.createSchedule(requestModel);
    }

    public Observable<ResultModel<List<SchedulesResponseModel>>> findSchedules(AddFuelStationRequestModel requestModel) {
        return api.findSchedules(requestModel);
    }

    public Observable<ResultModel<AddFuelRequestResponseModel>> updateSchedule(CreateScheduleRequestModel requestModel) {
        return api.updateSchedule(requestModel);
    }

    public Observable<ResultModel<List<SchedulesResponseModel>>> oldSchedules(AddFuelStationRequestModel requestModel) {
        return api.oldSchedules(requestModel);
    }

    public Observable<ResultModel<List<SchedulesResponseModel>>> searchSchedule(SearchScheduleRequestModel requestModel) {
        return api.searchSchedule(requestModel);
    }

    public Observable<ResultModel<VehicleByNumberResponseModel>> vehicleByNumber(VehicleByNumberRequestModel requestModel) {
        return api.vehicleByNumber(requestModel);
    }

    public Observable<ResultModel<List<ParkTransactionResponseModel>>> findParkTransactions(ParkTransRequestModel requestModel) {
        return api.findParkTransactions(requestModel);
    }

    public Observable<ResultModel<CreateTankRequestModel>> createTank(CreateTankRequestModel requestModel) {
        return api.createTank(requestModel);
    }

    public Observable<ResultModel<List<TankResponseModel>>> findTank(SearchScheduleRequestModel requestModel) {
        return api.findTank(requestModel);
    }

    public Observable<ResultModel<FuelStationAvailabilityRequestModel>> setStationAvailability(FuelStationAvailabilityRequestModel requestModel) {
        return api.setStationAvailability(requestModel);
    }

    public Observable<ResultModel<CreateTankRequestModel>> updateTank(CreateTankRequestModel requestModel) {
        return api.updateTank(requestModel);

    }

    public Observable<ResultModel<List<SaleFuelTypeResponseModel>>> findSaleByFuelType(SearchScheduleRequestModel requestModel) {
        return api.findSaleByFuelType(requestModel);
    }

    public Observable<ResultModel> requestParkTransaction(AddRefuelRequestModel refuelRequestModel) {
        return api.requestParkTransaction(refuelRequestModel);
    }

    public Observable<ResultModel> createCashTransaction(AddRefuelRequestModel refuelRequestModel) {
        return api.createCashTransaction(refuelRequestModel);
    }

    public Observable<ResultModel<List<VerifyVehicleModel>>> verifyTransaction(VerifyTransactionRequestModel requestModel) {
        return api.verifyTransaction(requestModel);
    }

    public Observable<ResultModel<List<ViewCashTransactionResponseModel>>> findCashTransactions(PaginationModel paginationModel) {
        return api.findCashTransaction(paginationModel);
    }

    public Observable<ResultModel<List<ViewCashTransactionResponseModel>>> findCashTransactions(SearchScheduleRequestModel searchScheduleRequestModel) {
        return api.findCashTransaction(searchScheduleRequestModel);
    }

    public Observable<ResultModel<List<OnlineTransactionsResponseModel>>> findOnlineTransactions(SearchScheduleRequestModel requestModel) {
        return api.findOnlineTransaction(requestModel);
    }

    public Observable<ResultModel> dispenseVerifyTrans(DispenseTransRequestModel requestModel) {
        return api.dispenseVerifyTrans(requestModel);
    }

    public Observable<ResultModel> registerDevice(RegisterRequest requestModel) {
        return api.registerDevice(requestModel);
    }

    public Observable<ResultModel<PaymentHistoryResponseModel>> getTransDetail(TransDetailRequestModel requestModel) {
        return api.getTransDetail(requestModel);
    }

    public Observable<ResultModel> refuelRequestDriver(RefuelRequestDriverModel requestModel) {
        return api.refuelRequestDriver(requestModel);
    }

    public Observable<ResultModel> deleteTank(ExtraNotificationModel requestModel) {
        return api.deleteTank(requestModel);
    }

    public Observable<ResultModel> agreementRequest(AgreementRequestModel requestModel) {
        return api.agreementRequest(requestModel);
    }

    public Observable<ResultModel> deletePendingRefuel(DeleteDriverRequestModel requestModel) {
        return api.deletePendingRefuel(requestModel);
    }

    public Observable<ResultModel<CreditAgreementResponseModel>> agreementDetails(AgreementRequestModel requestModel) {
        return api.agreementDetail(requestModel);
    }

    public Observable<ResultModel> logout(RegisterRequest registerRequest) {
        return api.logout(registerRequest);
    }

    public Observable<ResultModel<SettingsResponseModel>> getSettings(SettingsRequestModel requestModel) {
        return api.getSettings(requestModel);
    }

    public Observable<ResultModel<OperatorsResponseModel>> findOperatorByNumber(FindOperatorRequestModel requestModel) {
        return api.findOperatoryByNumber(requestModel);
    }

    public Observable<ResultModel<List<VerifyVehicleModel>>> verifyTransByQrCode(DeleteDriverRequestModel requestModel) {
        return api.verifyTransByQrCode(requestModel);
    }

    public Observable<ResultModel<List<NotificationResponseModel>>> getNotifications(PaginationModel requestModel) {
        return api.getNotifications(requestModel);
    }

    public Observable<ResultModel> transRecieved(TransRecievedRequestModel requestModel) {
        return api.transRecieved(requestModel);
    }

    public Observable<ResultModel> verifyPassword(ChangePasswordRequestModel requestModel) {
        return api.verifyPassword(requestModel);
    }

    public Observable<ResultModel<TotalResponse>> getTotal(TotalRequest totalRequest) {
        return api.getTotal(totalRequest);
    }

    public Observable<ResultModel> updateOperator(UpdateOperatorRequestModel requestModel) {
        return api.updateOperator(requestModel);
    }

    public Observable<ResultModel<RefuelingResponseModel>> reportRefuel(ReportRequestModel requestModel) {
        return api.refuelReport(requestModel);
    }

    public Observable<ResultModel> updateTankType(TankTypeUpdateRequest request) {
        return api.updateTankType(request);
    }

    public Observable<ResultModel<List<GetFuelStationResponseModel>>> getFuelStationOwners() {
        return api.getFuelStationOwners();
    }

    public Observable<ResultModel> switchCreditAgreement(SwitchCreditAgreementReqModel reqModel) {
        return api.switchCreditAgreement(reqModel);
    }

}
