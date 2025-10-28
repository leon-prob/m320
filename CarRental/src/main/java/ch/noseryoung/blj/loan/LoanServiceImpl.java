package ch.noseryoung.blj.loan;

import ch.noseryoung.blj.exceptions.loanExceptions.*;
import ch.noseryoung.blj.exceptions.objectNotFound.LoanContractNotFound;
import ch.noseryoung.blj.car.Car;
import ch.noseryoung.blj.car.CarService;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.login.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Service
@Component
@Primary
@Log4j2
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;
    private final CarService mediaService;
    private final UserService userService;

    public LoanServiceImpl(LoanRepository loanRepository, CarService mediaService, UserService userService) {
        this.loanRepository = loanRepository;
        this.mediaService = mediaService;
        this.userService = userService;
    }

    @Override
    public List<LoanContract> getAllLoans() {
        log.info("Getting all loan contracts");
        return loanRepository.findAll();
    }

    @Override
    public LoanContract getLoanById(UUID id) {
        return loanRepository.findById(id).orElseThrow(LoanContractNotFound::new);
    }

    @Override
    public LoanContract createLoan(LoanContract loanContract) {
        return loanRepository.save(loanContract);
    }

    @Override
    public LoanContract createLoan(UUID userId, UUID mediaId, LocalDate startDate, LocalDate endDate, LoanStatus status) {
        Car loaningCar = mediaService.getCarById(mediaId);
        User loaningUser = userService.getUserById(userId);

        if(LocalDate.now().isBefore(endDate)){
            isCarAvailableNow(loaningCar);
        } else {
            isCarAvailableInPeriod(loaningCar, startDate, endDate, status);
        }

        isUserOldEnough(loaningCar, loaningUser);
        isUserCustomer(loaningUser);
        isValidStatus(status, endDate);
        datesAreInMaxLimitOfCarToLoan(loaningCar, startDate, endDate);
        log.info("New Loan created");
        return new LoanContract(startDate, endDate, status, loaningUser, loaningCar);
    }

    private void isValidStatus(LoanStatus status, LocalDate endDate){
        if(endDate.isAfter(LocalDate.now()) && status != LoanStatus.OPEN){
            throw new InvalidLoanStatusException();
        }
    }

    private void isUserCustomer(User user){
        if(user.isBanned()){
            throw new BannedUserException();
        }
    }

    private void isCarAvailableNow(Car media){
        for(LoanContract loanContract : loanRepository.findAll()){
            if(loanContract.getCar() == media && loanContract.getLoanStatus() != LoanStatus.COMPLETED){
                throw new CarAlreadyLoaned();
            }
        }
    }
    private void isCarAvailableInPeriod(Car media, LocalDate startDate, LocalDate endDate, LoanStatus status){
        for(LoanContract loanContract : loanRepository.findAll()){
            if(loanContract.getCar() == media){
                // first when new loan is within the other contract or otherwise then if it is cutting with end then with start date
                if((loanContract.getStartDate().isBefore(startDate) && loanContract.getEndDate().isAfter(endDate))
                        || (loanContract.getStartDate().isAfter(startDate) && loanContract.getEndDate().isBefore(endDate))
                        || (loanContract.getStartDate().isBefore(endDate) && loanContract.getEndDate().isAfter(endDate))
                        || (loanContract.getStartDate().isBefore(startDate) && loanContract.getEndDate().isAfter(startDate))){
                    throw new CarAlreadyLoaned();
                }
                if(status != LoanStatus.COMPLETED && loanContract.getStartDate().isAfter(endDate)){
                    throw new LoanErrorException();
                }
            }
        }
    }

    private void isUserOldEnough(Car media, User user){
        if(media.getRequiredAge() > user.getAge()){
            throw new UserTooYoungToLoan();
        }
    }

    private void datesAreInMaxLimitOfCarToLoan(Car media, LocalDate startDate, LocalDate endDate){
        Period loanInquiryPeriod = Period.between(startDate, endDate);
        int loanInquiryDays = loanInquiryPeriod.getDays() + loanInquiryPeriod.getMonths() * 12 + loanInquiryPeriod.getYears() * 365;
        if(loanInquiryDays > media.getMaximumRentalPeriodInDays()){
            throw new TooLongInquiryException();
        }
    }

    @Override
    public LoanContract updateLoan(UUID id, LoanContract loanContract) {
        LoanContract changingContract = loanRepository.findById(id).orElseThrow(LoanContractNotFound::new);
        changingContract.setStartDate(loanContract.getStartDate());
        changingContract.setEndDate(loanContract.getEndDate());
        changingContract.setLoanStatus(loanContract.getLoanStatus());
        changingContract.setCar(loanContract.getCar());
        changingContract.setCustomer(loanContract.getCustomer());
        return loanRepository.save(changingContract);
    }

    @Override
    public void deleteLoanById(UUID id) {
        log.info("Loan got deleted");
        loanRepository.deleteById(id);
    }
}
