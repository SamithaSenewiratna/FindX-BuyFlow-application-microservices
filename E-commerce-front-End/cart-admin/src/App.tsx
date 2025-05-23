import React, { useState } from 'react';
import { Box,  Button, Paper, TextField, Typography, CssBaseline, Container,Link,Alert} from '@mui/material';

const AuthService = {
  login: async (email: string, password: string) => {
    return new Promise<{ data: { access_token: string; refresh_token: string } }>((resolve, reject) =>
      setTimeout(() => {
        if (email === 'test@example.com' && password === 'password') {
          resolve({
            data: {
              access_token: 'token123',
              refresh_token: 'refresh123',
            }
          });
        } else {
          reject(new Error('Invalid credentials'));
        }
      }, 1000)
    );
  }
};

function App() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await AuthService.login(email, password);
      const { access_token, refresh_token } = response.data;
      console.log('Access Token:', access_token);
      console.log('Refresh Token:', refresh_token);
    
    } catch (err: any) {
      setError(err.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  };

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
              onSubmit={handleLogin}
            >
              {error && (
                <Alert severity="error" sx={{ mb: 2 }}>
                  {error}
                </Alert>
              )}

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
                disabled={loading}
                sx={{
                  mt: 3,
                  py: 1.5,
                  borderRadius: 3,
                  textTransform: 'none',
                  fontWeight: 'bold',
                  fontSize: '1rem',
                }}
              >
                {loading ? 'Logging in...' : 'Login'}
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
