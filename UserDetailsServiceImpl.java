@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private EmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = employeeRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            emp.getUsername(),
            emp.getPassword(),
            emp.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList()
        );
    }
}
