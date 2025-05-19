import React, { useState } from 'react';
import { Box, Button, Paper, TextField, Typography, CssBaseline,  Container, Link} from '@mui/material';

function App() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  return (
    <>
      <CssBaseline />
      <Box
        sx={{
          background: 'linear-gradient(to right, #e0eafc, #cfdef3)',
          minHeight: '100vh',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
        }}
      >
        <Container maxWidth="sm">
          <Paper
            elevation={6}
            sx={{
              borderRadius: 4,
              padding: 5,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              boxShadow: '0px 10px 30px rgba(0,0,0,0.1)',
              backdropFilter: 'blur(8px)',
            }}
          >
            <Typography variant="h3" fontWeight="bold" color="primary.main" gutterBottom>
              sellX
            </Typography>

            <Typography variant="h6" gutterBottom>
              Sign in to your account
            </Typography>

            <Typography variant="body2" color="text.secondary" textAlign="center" mb={2}>
              Welcome back! Please enter your login details below to access your dashboard.
            </Typography>

            <Box
              component="form"
              sx={{ width: '100%', mt: 2 }}
            >
              <TextField
                fullWidth
                label="Email address"
                placeholder="name@example.com"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                margin="normal"
                required
              />
              <TextField
                fullWidth
                label="Password"
                placeholder="••••••••"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                margin="normal"
                required
              />

              <Box display="flex" justifyContent="space-between" mt={1}>
                <Link href="#" variant="body2" underline="hover">
                  Forgot password?
                </Link>
              </Box>

              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                sx={{
                  mt: 3,
                  py: 1.5,
                  borderRadius: 3,
                  textTransform: 'none',
                  fontWeight: 'bold',
                  fontSize: '1rem',
                }}
              >
                Login
              </Button>

              <Typography variant="body2" align="center" mt={3}>
                Don't have an account?{' '}
                <Link href="#" underline="hover" color="primary">
                  Sign up
                </Link>
              </Typography>
            </Box>
          </Paper>
        </Container>
      </Box>
    </>
  );
}

export default App;
